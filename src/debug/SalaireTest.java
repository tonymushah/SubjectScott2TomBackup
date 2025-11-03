package debug;

import java.util.Arrays;
import java.util.List;

import main.backend.salaire.SalaireCalculator;
import main.common.map.IRSA;

public class SalaireTest {
    public static final double epsilon = 0.000001d;

    public static void assert_double(double d1, double expected) {
        if (Math.abs(expected - d1) > epsilon) {
            throw new RuntimeException(String.format("Expected %f, got %f", expected, d1));
        }
    }

    public static void main(String[] args) {
        List<IRSA> irsas = getIrsas();

        SalaireTest.assert_double(SalaireCalculator.calculer_irsa_by_entries(irsas, 0), 0);
        SalaireTest.assert_double(SalaireCalculator.calculer_irsa_by_entries(irsas, 900), 50);
        SalaireTest.assert_double(SalaireCalculator.calculer_irsa_by_entries(irsas, 1500), 74.95);
        SalaireTest.assert_double((SalaireCalculator.calculer_irsa_by_entries(irsas, 2500)), 149.85);
        SalaireTest.assert_double((SalaireCalculator.calculer_irsa_by_entries(irsas, 7000)), 949.5);
    }

    private static List<IRSA> getIrsas() {
        List<IRSA> irsas = Arrays.asList(
                IRSA.createFixVal(null, Double.valueOf(1000), Double.valueOf(50)),
                IRSA.createPercentVal(Double.valueOf(1001), Double.valueOf(2000), Double.valueOf(5)),
                IRSA.createPercentVal(Double.valueOf(2001), Double.valueOf(3000), Double.valueOf(10)),
                IRSA.createPercentVal(Double.valueOf(3001), Double.valueOf(4000), Double.valueOf(15)),
                IRSA.createPercentVal(Double.valueOf(4001), null, Double.valueOf(20)));
        return irsas;
    }
}
