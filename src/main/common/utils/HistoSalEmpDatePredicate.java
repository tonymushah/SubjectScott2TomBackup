package main.common.utils;

public class HistoSalEmpDatePredicate {
    private Integer EMPNO;
    private java.sql.Date HISTO_DATE;

    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer eMPNO) {
        EMPNO = eMPNO;
    }

    public java.sql.Date getHISTO_DATE() {
        return HISTO_DATE;
    }

    public void setHISTO_DATE(java.sql.Date hISTO_DATE) {
        HISTO_DATE = hISTO_DATE;
    }

    public HistoSalEmpDatePredicate(Integer eMPNO, java.sql.Date hISTO_DATE) {
        EMPNO = eMPNO;
        HISTO_DATE = hISTO_DATE;
    }

    public HistoSalEmpDatePredicate() {
    }

}
