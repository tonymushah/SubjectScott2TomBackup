package debug;
import main.base.Err.FromStringException;
import main.base.Err.NoDataToUpdateErr;
import main.base.context.DBconnect;
import main.base.func.special.SpecialQueryBuilder;
import main.base.func.sql.DBClassManager;
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
      // //  Connection conn = DBconnect.connect();
      // //  newEmp.insertByConn(conn);
      // //  for (Object o : newEmp.find(conn)) {
      // //  System.out.println(""+o.toString());
      // //  } 
      // //  newEmp.deleteByConn(conn);
      // //  conn.commit();
      // System.out.println(""+newEmp.getFieldToString("EMPNO"));
      PreparedStatement ps=SpecialQueryBuilder.getPstmtFor_SALAIRE_EMP(DBconnect.connect(), "2026-10-25", "V_SALAIRE_EMP_PROCHE");
      DBClassManager.findObject0(ps,new V_SALAIRE_EMP_PROCHE());

    }
  }