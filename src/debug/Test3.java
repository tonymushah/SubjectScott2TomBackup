package debug;

import java.sql.SQLException;

import main.base.func.special.SpecialQueryBuilder;
import main.map.HISTOSAL;

public class Test3 {
    public static void main(String[] args) throws NoSuchFieldException,SQLException {
        SpecialQueryBuilder.getNameDisplay(HISTOSAL.class.getDeclaredField("EMPNO"));
    }
}
