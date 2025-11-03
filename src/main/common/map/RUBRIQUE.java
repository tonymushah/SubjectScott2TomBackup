package main.common.map;

import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;
import main.common.annotation.SkipFormulaire;

public class RUBRIQUE implements SQLMapTable, SetableFromString {

    @SkipFormulaire
    private Integer RUBNO;
    private String RUBNOM;
    private String RUBTYPE;
    private String RUBMODE;

    public static final String RUBTYPE_GAIN = "GAIN";
    public static final String RUBTYPE_RETENUE = "RETENUE";

    public static final String RUBMODE_FIXE = "FIXE";
    public static final String RUBMODE_CALCULE = "CALCULE";

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

    public RUBRIQUE() {
    }

    public RUBRIQUE(V_RUB_HISTO_SAL v_RUB_HISTO_SAL) {
        this.setRUBMODE(v_RUB_HISTO_SAL.getRUBMODE());
        this.setRUBNO(v_RUB_HISTO_SAL.getRUBNO());
        this.setRUBTYPE(v_RUB_HISTO_SAL.getRUBTYPE());
        this.setRUBNOM(v_RUB_HISTO_SAL.getRUBNOM());
    }
}
