package main.common.map;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import main.backend.base.context.DBconnect;
import main.backend.base.func.special.SpecialQueryBuilder;
import main.backend.base.func.sql.DBClassManager;
import main.backend.base.func.util.trait.SQLMap;
import main.backend.base.func.util.trait.SetableFromString;

public class V_SALAIRE_EMP_PROCHE implements SetableFromString, SQLMap {

    public V_SALAIRE_EMP_PROCHE() {

    }

    Integer EMPNO;

    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer eMPNO) {
        EMPNO = eMPNO;
    }

    String ENAME;

    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String eNAME) {
        ENAME = eNAME;
    }

    Date DATE_SAL;

    public Date getDATE_SAL() {
        return DATE_SAL;
    }

    public void setDATE_SAL(Date dATE_SAL) {
        DATE_SAL = dATE_SAL;
    }

    Double SALAIRE_A_PAYER;

    public Double getSALAIRE_A_PAYER() {
        return SALAIRE_A_PAYER;
    }

    public void setSALAIRE_A_PAYER(Double sALAIRE_A_PAYER) {
        SALAIRE_A_PAYER = sALAIRE_A_PAYER;
    }

    public static Vector<Object> findEmp(String date, EMP where) throws SQLException, ReflectiveOperationException {
        Connection conn = DBconnect.connect();
        String viewToUse = SpecialQueryBuilder.isGeDateToNow(date) ? "V_SALAIRE_EMP_TODAY" : "V_SALAIRE_EMP_PROCHE";
        System.out.println("" + viewToUse);
        Vector<Object> results = DBClassManager.findObject0(
                SpecialQueryBuilder.getPstmtFor_SALAIRE_EMP(
                        conn, date,
                        viewToUse, where),
                V_SALAIRE_EMP_PROCHE.class);
        conn.close();
        return results;
    }

    public static Vector<Object> get2dernierSalaire(EMP where,String date) throws SQLException, ReflectiveOperationException {
        Connection conn = DBconnect.connect();
          Vector<Object> results = DBClassManager.findObject0(
                SpecialQueryBuilder.getPstmtFor_SALAIRE_2Dernier(
                        conn, date,
                         where),
                V_SALAIRE_EMP_PROCHE.class);
        conn.close();
        return results;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("V_SALAIRE_EMP_PROCHE{");
        sb.append("EMPNO=").append(EMPNO);
        sb.append(", ENAME='").append(ENAME != null ? ENAME : "null").append('\'');
        sb.append(", DATE_SAL=").append(DATE_SAL != null ? DATE_SAL.toString() : "null");
        sb.append(", SALAIRE_A_PAYER=").append(SALAIRE_A_PAYER);
        sb.append('}');
        return sb.toString();
    }
}
