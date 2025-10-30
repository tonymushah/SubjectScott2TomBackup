package main.common.map;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import main.backend.base.context.DBconnect;
import main.backend.base.func.special.SpecialQueryBuilder;
import main.backend.base.func.sql.DBClassManager;
import main.backend.base.func.util.trait.SetableFromString;
import main.common.annotation.IdDropDown;

public class V_SALAIRE_DEPT_PROCHE implements SetableFromString {
    @IdDropDown(classtoJoin=DEPT.class , foreingkey = "DEPTNO" , todisplay = "DNAME") 
    Integer DEPTNO;

    public Integer getDEPTNO() {
        return DEPTNO;
    }

    public void setDEPTNO(Integer dEPTNO) {
        DEPTNO = dEPTNO;
    }

    String DNAME;

    public String getDNAME() {
        return DNAME;
    }

    public void setDNAME(String dNAME) {
        DNAME = dNAME;
    }

    Integer SALAIRE_A_PAYER;

    public Integer getSALAIRE_A_PAYER() {
        return SALAIRE_A_PAYER;
    }

    public void setSALAIRE_A_PAYER(Integer sALAIRE_A_PAYER) {
        SALAIRE_A_PAYER = sALAIRE_A_PAYER;
    }

    Integer NOMBRE_EMP;

    public Integer getNOMBRE_EMP() {
        return NOMBRE_EMP;
    }

    public void setNOMBRE_EMP(Integer nOMBRE_EMP) {
        NOMBRE_EMP = nOMBRE_EMP;
    }

    public static Vector<Object> findDept(String date,V_SALAIRE_DEPT_PROCHE where) throws SQLException, ReflectiveOperationException {
        Connection conn = DBconnect.connect();
        String viewToUse = SpecialQueryBuilder.isGeDateToNow(date) ? "V_SALAIRE_EMP_TODAY" : "V_SALAIRE_EMP_PROCHE";
        System.out.println("" + viewToUse);
        Vector<Object> results = DBClassManager.findObject0(
                SpecialQueryBuilder.getPstmtFor_SALAIRE_DEPT(conn, date, viewToUse,where),
                V_SALAIRE_DEPT_PROCHE.class);
        conn.close();
        return results;
    }

    @Override
    public String toString(){
        return "DEPT{" +
                "DEPTNO=" + DEPTNO +
                ", DNAME='" + DNAME + '\'' +
                ", SALAIRE_A_PAYER='" + SALAIRE_A_PAYER + '\'' +
                ", NOMBRE_EMP=" + NOMBRE_EMP +
                '}';
    }
}
