package debug;

import java.sql.SQLException;
import java.util.Vector;

import main.common.map.EMP;
import main.common.map.V_SALAIRE_EMP_PROCHE;

public class Test3 {
  public static void main(String[] args) throws SQLException, ReflectiveOperationException {
    EMP emp = new EMP();
    emp.setEMPNO(7499);
    Vector<V_SALAIRE_EMP_PROCHE> lo = V_SALAIRE_EMP_PROCHE.get2dernierSalaire(emp, "2026-10-30");
    System.out.println("" + lo.get(0));
    System.out.println("" + lo.get(1));

  }
}
