package debug;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import main.backend.salaire.SalaireCalculator;
import main.common.map.RUBCONF;
import main.common.map.RUBRIQUE;
import main.common.map.V_RUB_HISTO_SAL;

public class SalCalcTest {
    public static void main(String[] args) {
        List<RUBCONF> rubconfs = Arrays.asList(RUBCONF.createStaticConf(1, 20d, 50d));
        List<V_RUB_HISTO_SAL> bHisto_SALs = Arrays.asList(
                V_RUB_HISTO_SAL.createTestHistoSal(0, "Salaire Brut", RUBRIQUE.RUBMODE_FIXE, RUBRIQUE.RUBTYPE_GAIN,
                        1000d),
                V_RUB_HISTO_SAL.createTestHistoSal(1, "CNAPS", RUBRIQUE.RUBMODE_CALCULE, RUBRIQUE.RUBTYPE_RETENUE,
                        10d),
                V_RUB_HISTO_SAL.createTestHistoSal(2, "Indaminte de Longement", RUBRIQUE.RUBMODE_FIXE,
                        RUBRIQUE.RUBTYPE_GAIN, 25d),
                V_RUB_HISTO_SAL.createTestHistoSal(3, "Retard", RUBRIQUE.RUBMODE_FIXE, RUBRIQUE.RUBTYPE_RETENUE, 15d));
        SalaireCalculator calculator = new SalaireCalculator(null);
        HashMap<String, Double> sal_fixes = calculator.histo_fixe_list(bHisto_SALs);
        double gain_imposable_total = calculator.gain_imposable_total(sal_fixes);

        SalaireTest.assert_double(gain_imposable_total, 1010d);

        HashMap<String, Double> sal_calc = calculator.gain_calculable(gain_imposable_total,
                calculator.histo_calcule_list(bHisto_SALs), rubconfs);
        double gain_calculable_total = calculator.gain_imposable_total(sal_calc);
        SalaireTest.assert_double(gain_calculable_total, -50d);

    }
}
