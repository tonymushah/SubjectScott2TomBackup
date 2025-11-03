package main.backend.salaire;

import java.sql.Connection;
import java.util.List;

import main.common.map.IRSA;

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

    @Override
    public void close() throws Exception {
        connection.close();
    }

}
