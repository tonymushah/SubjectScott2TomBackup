package main.backend.salaire;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import main.common.map.IRSA;
import main.common.map.RUBCONF;
import main.common.map.RUBRIQUE;
import main.common.map.V_RUB_HISTO_SAL;

public class SalaireCalculator implements AutoCloseable {
    private Connection connection;

    public SalaireCalculator(Connection connection) {
        this.connection = connection;
    }

    public static double calculer_irsa_by_entries(List<IRSA> irsas, double sal_brut) {
        if (sal_brut == 0) {
            return 0;
        }
        double fin_val = 0;
        for (IRSA irsa : irsas) {
            if (irsa.getINFE() == null && irsa.getSUPE() != null && irsa.getVAL() != null) {
                fin_val += irsa.getVAL();
            } else if (irsa.getINFE() != null && irsa.getSUPE() != null && irsa.getVAL_PER() != null) {
                if (irsa.getINFE() <= sal_brut && sal_brut < irsa.getSUPE()) {
                    fin_val += ((sal_brut - irsa.getINFE()) * (irsa.getVAL_PER() / 100));
                    break;
                } else if (irsa.getINFE() <= sal_brut && sal_brut > irsa.getSUPE()) {
                    fin_val += ((irsa.getSUPE() - irsa.getINFE()) * (irsa.getVAL_PER() / 100));
                }
            } else if (irsa.getINFE() != null && irsa.getSUPE() == null && irsa.getVAL_PER() != null) {
                if (irsa.getINFE() <= sal_brut) {
                    fin_val += ((sal_brut - irsa.getINFE()) * (irsa.getVAL_PER() / 100));
                }
            }
        }
        return fin_val;
    }

    public HashMap<String, Double> histo_fixe_list(List<V_RUB_HISTO_SAL> complete_histo_sal) {
        HashMap<String, Double> map = new HashMap<>();
        for (V_RUB_HISTO_SAL v_RUB_HISTO_SAL : complete_histo_sal) {
            if (v_RUB_HISTO_SAL.getRUBMODE() == RUBRIQUE.RUBMODE_FIXE) {
                double val;
                if (v_RUB_HISTO_SAL.getRUBTYPE().trim() == RUBRIQUE.RUBTYPE_GAIN) {
                    val = v_RUB_HISTO_SAL.getHISTO_VAL();
                } else if (v_RUB_HISTO_SAL.getRUBTYPE().trim() == RUBRIQUE.RUBTYPE_RETENUE) {
                    val = v_RUB_HISTO_SAL.getHISTO_VAL() * -1.0;
                } else {
                    val = 0;
                }
                map.put(v_RUB_HISTO_SAL.getRUBNOM(), val);
            }
        }
        return map;
    }

    public List<V_RUB_HISTO_SAL> histo_calcule_list(List<V_RUB_HISTO_SAL> complete_histo_sal) {
        List<V_RUB_HISTO_SAL> map = new Vector<>();
        for (V_RUB_HISTO_SAL v_RUB_HISTO_SAL : complete_histo_sal) {
            if (v_RUB_HISTO_SAL.getRUBMODE() == RUBRIQUE.RUBMODE_CALCULE) {
                map.add(v_RUB_HISTO_SAL);
            }
        }
        return map;
    }

    public double gain_imposable_total(HashMap<String, Double> histo_fix_list) {
        return histo_fix_list.values().stream().collect(Collectors.summarizingDouble(Double::doubleValue)).getSum();
    }

    public HashMap<String, Double> gain_calculable(double gain_imposable_total,
            List<V_RUB_HISTO_SAL> histo_calcule_list, List<RUBCONF> rubconfs) {

        HashMap<String, Double> map = new HashMap<>();
        for (V_RUB_HISTO_SAL v_RUB_HISTO_SAL : histo_calcule_list) {
            double val;
            val = ((v_RUB_HISTO_SAL.getHISTO_VAL() * gain_imposable_total) / 100);
            RUBCONF rubconf = null;
            for (RUBCONF rubconf2 : rubconfs) {
                if (rubconf2.getRUBNO() == v_RUB_HISTO_SAL.getRUBNO()) {
                    if (rubconf2.getMAX() != null && rubconf2.getMIN() != null) {
                        rubconf = rubconf2;
                    }
                }
            }
            if (rubconf != null) {
                if (val < rubconf.getMIN()) {
                    val = rubconf.getMIN();
                } else if (val > rubconf.getMAX()) {
                    val = rubconf.getMAX();
                }
            }
            if (v_RUB_HISTO_SAL.getRUBTYPE() == RUBRIQUE.RUBTYPE_RETENUE) {
                val *= -1.0;
            }
            map.put(v_RUB_HISTO_SAL.getRUBNOM(), val);
        }
        return map;
    }

    public FicheSalaire get_FicheSalaire(List<V_RUB_HISTO_SAL> sHisto_SALs, List<RUBCONF> rubconfs, List<IRSA> irsas) {
        HashMap<String, Double> sal_fixes = this.histo_fixe_list(sHisto_SALs);
        double gain_imposable_total = this.gain_imposable_total(sal_fixes);

        HashMap<String, Double> sal_calc = this.gain_calculable(gain_imposable_total,
                this.histo_calcule_list(sHisto_SALs), rubconfs);
        double gain_calculable_total = this.gain_imposable_total(sal_calc);

        double irsa = calculer_irsa_by_entries(irsas, gain_imposable_total);

        double salaire_net = gain_calculable_total + gain_imposable_total - irsa;
        HashMap<String, Double> rubrique = new HashMap<>();

        rubrique.putAll(sal_fixes);
        rubrique.putAll(sal_calc);
        rubrique.put("IRSA", irsa);

        return new FicheSalaire(rubrique, gain_imposable_total, salaire_net);
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

}
