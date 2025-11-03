package main.common.map;

import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;

public class IRSA implements SQLMapTable, SetableFromString {
    private Double INFE;
    private Double SUPE;
    private Double VAL;

    public Double getINFE() {
        return INFE;
    }

    public void setINFE(Double iNFE) {
        INFE = iNFE;
    }

    public Double getSUPE() {
        return SUPE;
    }

    public void setSUPE(Double sUPE) {
        SUPE = sUPE;
    }

    public Double getVAL() {
        return VAL;
    }

    public void setVAL(Double vAL) {
        VAL = vAL;
    }

    public IRSA() {
    }

}
