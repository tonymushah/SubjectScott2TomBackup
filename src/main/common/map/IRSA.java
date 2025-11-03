package main.common.map;

import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;

public class IRSA implements SQLMapTable, SetableFromString {
    private Double INFE;
    private Double SUPE;
    private Double VAL;
    /// Valeur en pourcent
    private Double VAL_PER;

    
    public Double getINFE() {
        return INFE;
    }

    public Double getVAL_PER() {
        return VAL_PER;
    }

    public void setVAL_PER(Double vAL_PER) {
        VAL_PER = vAL_PER;
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

    public static IRSA createFixVal(Double INF, Double SUP, Double VAL) {
        IRSA irsa = new IRSA();
        irsa.setINFE(INF);
        irsa.setSUPE(SUP);
        irsa.setVAL(VAL);
        return irsa;
    }
    public static IRSA createPercentVal(Double INF, Double SUP, Double VAL) {
        IRSA irsa = new IRSA();
        irsa.setINFE(INF);
        irsa.setSUPE(SUP);
        irsa.setVAL_PER(VAL);
        return irsa;
    }
}
