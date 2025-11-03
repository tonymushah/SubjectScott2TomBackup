package main.common.map;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import main.backend.base.func.sql.DBClassManager;
import main.backend.base.func.util.trait.SQLMapTable;
import main.common.utils.HistoSalEmpDatePredicate;

public class V_RUB_HISTO_SAL implements SQLMapTable {
    private Integer RUBNO;
    private String RUBNOM;
    private String RUBTYPE;
    private String RUBMODE;
    private java.sql.Date HISTO_DATE;
    private Integer HISTO_EMP;
    private Double HISTO_VAL;

    public Integer getRUBNO() {
        return RUBNO;
    }

    public void setRUBNO(Integer rUBNO) {
        RUBNO = rUBNO;
    }

    public String getRUBNOM() {
        return RUBNOM;
    }

    public void setRUBNOM(String rUBNOM) {
        RUBNOM = rUBNOM;
    }

    public String getRUBTYPE() {
        return RUBTYPE;
    }

    public void setRUBTYPE(String rUBTYPE) {
        RUBTYPE = rUBTYPE;
    }

    public String getRUBMODE() {
        return RUBMODE;
    }

    public void setRUBMODE(String rUBMODE) {
        RUBMODE = rUBMODE;
    }

    public java.sql.Date getHISTO_DATE() {
        return HISTO_DATE;
    }

    public void setHISTO_DATE(java.sql.Date hISTO_DATE) {
        HISTO_DATE = hISTO_DATE;
    }

    public Integer getHISTO_EMP() {
        return HISTO_EMP;
    }

    public void setHISTO_EMP(Integer hISTO_EMP) {
        HISTO_EMP = hISTO_EMP;
    }

    public Double getHISTO_VAL() {
        return HISTO_VAL;
    }

    public void setHISTO_VAL(Double hISTO_VAL) {
        HISTO_VAL = hISTO_VAL;
    }

    public V_RUB_HISTO_SAL() {
    }

    public static Vector<V_RUB_HISTO_SAL> get_histo_sal_emp_date(Connection connection, java.sql.Date date, int empno)
            throws SQLException, ReflectiveOperationException {
        return DBClassManager.findObjectByRetour(connection, new HistoSalEmpDatePredicate(empno, date),
                V_RUB_HISTO_SAL.class, "V_RUB_HISTO_SAL");
    }

    public static V_RUB_HISTO_SAL createTestHistoSal(int RUBNO, String RUBNOM, String RUBMODE, String RUBTYPE,
            Double HISTO_VAL) {
        V_RUB_HISTO_SAL sal = new V_RUB_HISTO_SAL();
        sal.setHISTO_VAL(HISTO_VAL);
        sal.setRUBMODE(RUBMODE);
        sal.setRUBNO(RUBNO);
        sal.setRUBNOM(RUBNOM);
        sal.setRUBTYPE(RUBTYPE);
        return sal;
    }
}
