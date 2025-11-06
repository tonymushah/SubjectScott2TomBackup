package main.frontend.search.helpers;

public class FicheSalaireGetParam {
    private String date;
    private Integer empno;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public FicheSalaireGetParam(String date, Integer empno) {
        this.date = date;
        this.empno = empno;
    }

    public FicheSalaireGetParam() {
    }
}
