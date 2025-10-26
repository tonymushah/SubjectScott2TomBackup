package main.frontend.display;

import java.lang.reflect.Field;

public class FieldHelper {
     public static String getNameHTML(Field field) {
        return  field.getName();
    }

    public static boolean isNumericType(Class<?> type) {
        if (Number.class.isAssignableFrom(type))
            return true;
        return type == int.class || type == long.class ||
                type == double.class || type == float.class ||
                type == short.class || type == byte.class;
    }
}
