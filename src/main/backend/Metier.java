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
import main.map.V_SALAIRE_EMP_PROCHE;

public class Metier {
        public static void insertNewHisto(PrintWriter debuger, Map<String, String[]> allParameters)
                        throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {

                Connection conn = DBconnect.connect();

                EMP emp = new EMP();
                emp.fillSetable(allParameters);

                EMP empwhere = new EMP();
                emp.setEMPNO(emp.getEMPNO());

                if (allParameters.get("DATE_SAL") != null || !allParameters.get("DATE_SAL")[0].isEmpty()) {
                        Vector<Object> historics=V_SALAIRE_EMP_PROCHE.findEmp(allParameters.get("DATE_SAL")[0], empwhere);
                        if(((V_SALAIRE_EMP_PROCHE)(historics.get(0))).getSALAIRE_A_PAYER()==emp.getSAL()) {
                       // emp.setEMPNO(null);
                        empwhere.updateByConn(conn, empwhere);
                        }else{
                        emp.setEMPNO(null);
                        empwhere.updateByConn(conn, empwhere);
                        HISTOSAL newHistosal=new HISTOSAL((V_SALAIRE_EMP_PROCHE)(historics.get(0)));
                        newHistosal.insertByConn(conn);
                        };
                }

                // HISTOSAL oldHistosal

                // EMP empwhere=new EMP();
                // EMP empvalues=new EMP();
                // empwhere.setEMPNO(newHisto.getEMPNO());
                // empvalues.setSAL(newHisto.getSAL());

                // Double empLastMontant=((EMP )
                // empwhere.findByConn(conn).elementAt(0)).getSAL();
                // debuger.println(""+empvalues);
                // debuger.println(""+empwhere);
                // debuger.println(""+empLastMontant);

                // debuger.println("Update EMP"+newHisto);
                // empvalues.updateByConn(conn, empwhere);

                // debuger.println("INSERT MONTANT "+empLastMontant);

                // newHisto.setSAL(empLastMontant);
                // newHisto.insertByConn(conn);
                // conn.commit();
                // conn.close();
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