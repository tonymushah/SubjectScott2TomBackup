package main.map;
import java.sql.Date;

import main.backend.base.annotation.IdDropDown;
import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;

public class HISTOSAL implements SQLMapTable,SetableFromString{
    Date DATE_SAL;
    Double MONTANT;

    @IdDropDown(classtoJoin=EMP.class , foreingkey = "EMPNO" , todisplay = "ENAME") 
    Integer EMPNO;
    public HISTOSAL() {
    }
    public HISTOSAL(V_SALAIRE_EMP_PROCHE view) {
        this.DATE_SAL=view.DATE_SAL;
        this.EMPNO=view.EMPNO;
        this.MONTANT=view.SALAIRE_A_PAYER;
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
