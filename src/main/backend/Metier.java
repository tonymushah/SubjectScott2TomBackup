package main.backend;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import main.backend.base.Err.NoDataToUpdateErr;
import main.backend.base.context.DBconnect;
import main.map.EMP;

public class Metier {
        public static void insertNewHisto(PrintWriter debuger,EMP newHisto,String date) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
                    Connection conn=DBconnect.connect();
        EMP empwhere=new EMP();
        EMP empvalues=new EMP();
        empwhere.setEMPNO(newHisto.getEMPNO());
        empvalues.setSAL(newHisto.getSAL());

        Double empLastMontant=((EMP ) empwhere.findByConn(conn).elementAt(0)).getSAL();
        debuger.println(""+empvalues);
        debuger.println(""+empwhere);
        debuger.println(""+empLastMontant);

        debuger.println("Update EMP"+newHisto);
        empvalues.updateByConn(conn, empwhere);

        debuger.println("INSERT MONTANT "+empLastMontant);
        
        newHisto.setSAL(empLastMontant);
        newHisto.insertByConn(conn);
        conn.commit();
        conn.close();
        }

        public static void insertNewEmp(PrintWriter debuger,EMP newEmp) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
        debuger.println(""+newEmp);
        Connection conn=DBconnect.connect();
        newEmp.insertByConn(conn);
        conn.commit();
        conn.close();
        }
}