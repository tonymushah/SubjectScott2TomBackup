package main.map;

import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;

public class DEPT implements SQLMapTable,SetableFromString {
    Integer DEPTNO;
    String DNAME;
    String LOC;
    public DEPT() {
    }
    public Integer getDEPTNO() {
        return DEPTNO;
    }
    public void setDEPTNO(Integer dEPTNO) {
        DEPTNO = dEPTNO;
    }
    public String getDNAME() {
        return DNAME;
    }
    public void setDNAME(String dNAME) {
        DNAME = dNAME;
    }
    public String getLOC() {
        return LOC;
    }
    public void setLOC(String lOC) {
        LOC = lOC;
    }
}
