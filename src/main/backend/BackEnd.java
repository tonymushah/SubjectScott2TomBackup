package main.backend;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import main.base.Err.NoDataToUpdateErr;
import main.base.context.DBconnect;
import main.map.EMP;
import main.map.HISTOSAL;

public class BackEnd {
        public static void insertNewHisto(PrintWriter debuger,HISTOSAL newHisto) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
                    Connection conn=DBconnect.connect();
        EMP empwhere=new EMP();
        EMP empvalues=new EMP();
        empwhere.setEMPNO(newHisto.getEMPNO());
        empvalues.setSAL(newHisto.getMONTANT());

        Double empLastMontant=((EMP ) empwhere.findByConn(conn).elementAt(0)).getSAL();
        debuger.println(""+empvalues);
        debuger.println(""+empwhere);
        debuger.println(""+empLastMontant);

        debuger.println("Update EMP"+newHisto);
        empvalues.updateByConn(conn, empwhere);

        debuger.println("INSERT MONTANT "+empLastMontant);
        
        newHisto.setMONTANT(empLastMontant);
        newHisto.insertByConn(conn);
        conn.commit();
        conn.close();
        }
}