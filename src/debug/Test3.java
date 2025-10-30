package debug;

import java.sql.SQLException;

import main.backend.base.func.special.SpecialQueryBuilder;
import main.common.map.EMP;
public class Test3 {
    public static void main(String[] args) throws NoSuchFieldException,SQLException {
      SpecialQueryBuilder.getNameSelectable(EMP.class.getDeclaredField("MGR"));
    }
}
