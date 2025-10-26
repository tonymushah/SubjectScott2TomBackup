package main.map;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import main.base.context.DBconnect;
import main.base.func.special.SpecialQueryBuilder;
import main.base.func.sql.DBClassManager;

public class V_SALAIRE_EMP_PROCHE extends EMP {
    public V_SALAIRE_EMP_PROCHE() {

    }

    Date DATE_SAL;

    public Date getDATE_SAL() {
        return DATE_SAL;
    }

    public void setDATE_SAL(Date dATE_SAL) {
        DATE_SAL = dATE_SAL;
    }

    Double ANCIEN_MONTANT;

    public Double getANCIEN_MONTANT() {
        return ANCIEN_MONTANT;
    }

    public void setANCIEN_MONTANT(Double aNCIEN_MONTANT) {
        ANCIEN_MONTANT = aNCIEN_MONTANT;
    }

    public static Vector<Object> findEmp(String date, Object where) throws SQLException, ReflectiveOperationException {
        Connection conn = DBconnect.connect();
        Vector<Object> results = DBClassManager.findObject0(
                SpecialQueryBuilder.getPstmtFor_SALAIRE_EMP(
                        conn, date,
                        "V_SALAIRE_EMP_PROCHE", where),
                where.getClass());
        conn.commit();
        conn.close();
        return results;
    }

    
}