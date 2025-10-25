package main.map;

import java.sql.Date;
import main.base.func.util.trait.SQLMapTable;
import main.base.func.util.trait.SetableFromString;
public class EMP implements SQLMapTable,SetableFromString {
    private Integer EMPNO;      // NOT NULL NUMBER(4)
    private String ENAME;       // VARCHAR2(10)
    private String JOB;         // VARCHAR2(9)
    private Integer MGR;        // NUMBER(4)
    private Date HIREDATE;      // DATE
    private Double SAL;         // NUMBER(7,2)
    private Double COMM;        // NUMBER(7,2)
    private Integer DEPTNO;     // NOT NULL NUMBER(2)

    // Constructeurs
    public EMP() {}
    
    public EMP(Integer EMPNO, Integer DEPTNO) {
        this.EMPNO = EMPNO;
        this.DEPTNO = DEPTNO;
    }

    // Getters et Setters normaux
    public Integer getEMPNO() {
        return EMPNO;
    }

    public void setEMPNO(Integer EMPNO) {
        this.EMPNO = EMPNO;
    }
    public String getENAME() {
        return ENAME;
    }

    public void setENAME(String ENAME) {
        this.ENAME = ENAME;
    }

    public String getJOB() {
        return JOB;
    }

    public void setJOB(String JOB) {
        this.JOB = JOB;
    }

    public Integer getMGR() {
        return MGR;
    }

    public void setMGR(Integer MGR) {
        this.MGR = MGR;
    }
    public Date getHIREDATE() {
        return HIREDATE;
    }

    public void setHIREDATE(Date HIREDATE) {
        this.HIREDATE = HIREDATE;
    }

    public Double getSAL() {
        return SAL;
    }

    public void setSAL(Double SAL) {
        this.SAL = SAL;
    }
    public Double getCOMM() {
        return COMM;
    }

    public void setCOMM(Double COMM) {
        this.COMM = COMM;
    }
    public Integer getDEPTNO() {
        return DEPTNO;
    }

    public void setDEPTNO(Integer DEPTNO) {
        this.DEPTNO = DEPTNO;
    }


    // Méthode toString pour le debug
    @Override
    public String toString() {
        return "EMP{" +
                "EMPNO=" + EMPNO +
                ", ENAME='" + ENAME + '\'' +
                ", JOB='" + JOB + '\'' +
                ", MGR=" + MGR +
                ", HIREDATE=" + HIREDATE +
                ", SAL=" + SAL +
                ", COMM=" + COMM +
                ", DEPTNO=" + DEPTNO +
                '}';
    }
}