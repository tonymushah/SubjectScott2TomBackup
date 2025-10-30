package main.backend;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import main.backend.base.Err.NoDataToUpdateErr;
import main.backend.base.context.DBconnect;
import main.map.EMP;
import main.map.HISTOSAL;

public class Metier {
        public static void updateSal( PrintWriter debuger,HISTOSAL newHisto,Connection conn ) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
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
        }
        public static void insertNewHisto(PrintWriter debuger, Map<String, String[]> allParameters)
                        throws SQLException, ReflectiveOperationException {

                Connection conn = DBconnect.connect();

                EMP emp = new EMP();
                emp.fillSetable(allParameters);

                HISTOSAL histo = new HISTOSAL();
                histo.fillSetable(allParameters);

                EMP where = EMP.fromEMPNO(emp.getEMPNO());
                Vector<Object> lemp = where.find();

                try {
                        if (! (((EMP) lemp.get(0)).getSAL() == histo.getMONTANT())) {
                                updateSal(debuger , histo , conn);
                        }
                        emp.updateByConn(conn, where);
                } catch (NoDataToUpdateErr e) {

                }
                conn.commit();
                conn.close();
        }

        public static void insertNewEmp(PrintWriter debuger, EMP newEmp)
                        throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
                debuger.println("" + newEmp);
                Connection conn = DBconnect.connect();
                newEmp.insertByConn(conn);
                conn.commit();
                conn.close();
        }
}