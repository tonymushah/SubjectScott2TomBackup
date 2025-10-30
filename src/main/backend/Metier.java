package main.backend;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import main.backend.base.Err.NoDataToUpdateErr;
import main.backend.base.context.DBconnect;
import main.backend.base.func.sql.DBQueryManager;
import main.map.EMP;
import main.map.HISTOSAL;

public class Metier {
        public static boolean isValidToFind(Object e) {
                boolean maybe_retour = false;
                try {
                        maybe_retour = DBQueryManager.getColumnNotNull(e).size() > 0;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
                }
                return maybe_retour;
        }

        public static void updateSal(PrintWriter debuger, HISTOSAL newHisto, Connection conn)
                        throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
                EMP empwhere = new EMP();
                EMP empvalues = new EMP();
                empwhere.setEMPNO(newHisto.getEMPNO());
                empvalues.setSAL(newHisto.getMONTANT());
                Double empLastMontant = ((EMP) empwhere.findByConn(conn).elementAt(0)).getSAL();
                empvalues.updateByConn(conn, empwhere);
                newHisto.setMONTANT(empLastMontant);
                newHisto.insertByConn(conn);
        }

        public static void updateSal_Emp(EMP emp, HISTOSAL histo, Connection conn,
                        PrintWriter debuger) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
                EMP whereEmp = EMP.fromEMPNO(emp.getEMPNO());
                Vector<Object> lemp = whereEmp.find();
                if (!(((EMP) lemp.get(0)).getSAL() == histo.getMONTANT()) && isValidToFind(histo)) {
                        try {
                                updateSal(debuger, histo, conn);
                        } catch (Exception e) {
                                debuger.println("" + e);
                        }
                }
                emp.updateByConn(conn, whereEmp);
        }

        public static void insertNewHisto(PrintWriter debuger, Map<String, String[]> allParameters)
                        throws SQLException, ReflectiveOperationException {

                Connection conn = DBconnect.connect();

                EMP emp = new EMP();
                emp.fillSetable(allParameters);

                HISTOSAL histo = new HISTOSAL();
                histo.fillSetable(allParameters);

                try {
                        updateSal_Emp(emp, histo, conn, debuger);
                } catch (NoDataToUpdateErr e) {
                        debuger.println("" + emp);
                        debuger.println("" + e);
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