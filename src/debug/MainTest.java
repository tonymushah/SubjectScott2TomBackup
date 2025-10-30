package debug;
import main.backend.base.Err.FromStringException;
import main.backend.base.Err.NoDataToUpdateErr;
import main.map.EMP;
import main.map.V_SALAIRE_EMP_PROCHE;

//import main.base.context.DBconnect;
import java.sql.*;
public class MainTest {
    public static void main(String[] args) throws NoDataToUpdateErr, SQLException, ReflectiveOperationException, FromStringException {
            
      //       // TEST INSERT - Ajout d'un nouvel employé
      //       EMP newEmp = new EMP();
      //       newEmp.setEMPNO(7935);
      //       newEmp.setENAME("DUPONT");
      //       newEmp.setJOB("ANALYST");
      //       newEmp.setMGR(7566);  
      //       newEmp.setHIREDATE(Date.valueOf(LocalDate.now())); 
      //       newEmp.setSAL(3000.00);
      //       newEmp.setCOMM(500.00);
      //       newEmp.setDEPTNO(20);

      // System.out.println(""+newEmp);
      // newEmp.setFieldFromString("EMPNO","1");
      // newEmp.setFieldFromString("HIREDATE","2026-10-25");
      // // System.out.println(""+newEmp);
      // //  newEmp.insertByConn(conn);
      EMP p=new EMP();
      p.setDEPTNO(40);
      for (Object o : V_SALAIRE_EMP_PROCHE.findEmp("2026-10-25", p)) {
          System.out.println(""+((V_SALAIRE_EMP_PROCHE)o).getEMPNO());
      };
    }
  }