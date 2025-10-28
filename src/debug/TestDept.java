package debug;

import java.sql.SQLException;

import main.map.V_SALAIRE_DEPT_PROCHE;

public class TestDept {
    public static void main(String[] args) throws SQLException, ReflectiveOperationException {
        V_SALAIRE_DEPT_PROCHE dpt=new V_SALAIRE_DEPT_PROCHE();
        dpt.setDEPTNO(10);
        for (Object o :  V_SALAIRE_DEPT_PROCHE.findDept("2025-10-22",dpt)) {
          System.out.println(""+((V_SALAIRE_DEPT_PROCHE)o).getDEPTNO());
      };
    }
}
