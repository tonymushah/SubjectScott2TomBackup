package main.map;

import java.sql.Date;

import main.base.annotation.IdField;
import main.base.func.util.trait.SQLMapTable;
import main.base.func.util.trait.SetableFromString;

public class HISTOSAL implements SQLMapTable,SetableFromString{
    Date DATE_SAL;
    Double MONTANT;

    @IdField(classtoJoin=EMP.class , todisplay = "ENAME") 
    Integer EMPNO;
    public HISTOSAL() {
    }
    public Date getDATE_SAL() {
        return DATE_SAL;
    }
    public void setDATE_SAL(Date dATE_SAL) {
        DATE_SAL = dATE_SAL;
    }
    public Integer getEMPNO() {
        return EMPNO;
    }
    public void setEMPNO(Integer eMPNO) {
        EMPNO = eMPNO;
    }
    public Double getMONTANT() {
        return MONTANT;
    }
    public void setMONTANT(Double mONTANT) {
        MONTANT = mONTANT;
    }   
}
