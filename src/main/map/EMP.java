package main.map;

import java.sql.Date;

import main.backend.base.annotation.IdDropDown;
import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;
public class EMP implements SQLMapTable,SetableFromString {
    Integer EMPNO;     
    String ENAME;       
    String JOB;      

    @IdDropDown(classtoJoin=EMP.class , foreingkey = "EMPNO" , todisplay = "ENAME") 
    Integer MGR;       
    
    Date HIREDATE;     
    Double SAL;       
    Double COMM;       
    
    @IdDropDown(classtoJoin=DEPT.class , foreingkey = "DEPTNO" , todisplay = "DNAME") 
    Integer DEPTNO;     


    public EMP() {}
    
    public EMP(Integer EMPNO, Integer DEPTNO) {
        this.EMPNO = EMPNO;
        this.DEPTNO = DEPTNO;
    }


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