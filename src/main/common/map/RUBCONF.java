package main.common.map;

import main.backend.base.func.util.trait.SQLMapTable;
import main.backend.base.func.util.trait.SetableFromString;
import main.common.annotation.IdDropDown;

public class RUBCONF implements SQLMapTable, SetableFromString {
    @IdDropDown(classtoJoin = RUBRIQUE.class, foreingkey = "RUBNO", todisplay = "RUBNOM")
    private Integer RUBNO;
    private Double MIN;
    private Double MAX;

    public Integer getRUBNO() {
        return RUBNO;
    }

    public void setRUBNO(Integer rUBNO) {
        RUBNO = rUBNO;
    }

    public Double getMIN() {
        return MIN;
    }

    public void setMIN(Double mIN) {
        MIN = mIN;
    }

    public Double getMAX() {
        return MAX;
    }

    public void setMAX(Double mAX) {
        MAX = mAX;
    }

    public RUBCONF() {
    }

    public static RUBCONF createStaticConf(int RUBNO, Double MIN, Double MAX) {
        RUBCONF conf = new RUBCONF();
        conf.setMAX(MAX);
        conf.setMIN(MIN);
        conf.setRUBNO(RUBNO);
        return conf;
    }
}
