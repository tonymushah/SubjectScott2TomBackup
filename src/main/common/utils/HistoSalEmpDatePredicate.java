package main.common.utils;

public class HistoSalEmpDatePredicate {
    private Integer EMPNO;
    private String HISTO_DATE;

    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer eMPNO) {
        EMPNO = eMPNO;
    }

    public String getHISTO_DATE() {
        return HISTO_DATE;
    }

    public void setHISTO_DATE(String hISTO_DATE) {
        HISTO_DATE = hISTO_DATE;
    }

    public HistoSalEmpDatePredicate(Integer eMPNO, String hISTO_DATE) {
        EMPNO = eMPNO;
        HISTO_DATE = hISTO_DATE;
    }

    public HistoSalEmpDatePredicate() {
    }

}
