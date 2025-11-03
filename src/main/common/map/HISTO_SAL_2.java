package main.common.map;

import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;
import main.common.annotation.IdDropDown;

public class HISTO_SAL_2 implements SQLMapTable, SetableFromString {

    private String HISTO_DATE;
    @IdDropDown(classtoJoin = EMP.class, foreingkey = "EMPNO", todisplay = "ENAME")
    private Integer HISTO_EMP;

    @IdDropDown(classtoJoin = RUBRIQUE.class, foreingkey = "RUBNO", todisplay = "RUBNOM")
    private Integer HISTO_RUB;
    private Double HISTO_VAL;

    public HISTO_SAL_2() {
    }

    public String getHISTO_DATE() {
        return HISTO_DATE;
    }

    public void setHISTO_DATE(String hISTO_DATE) {
        HISTO_DATE = hISTO_DATE;
    }

    public Integer getHISTO_EMP() {
        return HISTO_EMP;
    }

    public void setHISTO_EMP(Integer hISTO_EMP) {
        HISTO_EMP = hISTO_EMP;
    }

    public Integer getHISTO_RUB() {
        return HISTO_RUB;
    }

    public void setHISTO_RUB(Integer hISTO_RUB) {
        HISTO_RUB = hISTO_RUB;
    }

    public Double getHISTO_VAL() {
        return HISTO_VAL;
    }

    public void setHISTO_VAL(Double hISTO_VAL) {
        HISTO_VAL = hISTO_VAL;
    }

    public HISTO_SAL_2(V_RUB_HISTO_SAL v_RUB_HISTO_SAL) {
        this.setHISTO_DATE(v_RUB_HISTO_SAL.getHISTO_DATE());
        this.setHISTO_EMP(v_RUB_HISTO_SAL.getHISTO_EMP());
        this.setHISTO_RUB(v_RUB_HISTO_SAL.getRUBNO());
        this.setHISTO_VAL(v_RUB_HISTO_SAL.getHISTO_VAL());
    }
}