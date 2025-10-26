package main.base.func.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

public class ReflectiveManager {
    public static Object getFieldObject(Field f, Object e) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
    String fieldName = f.getName();
    String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    java.lang.reflect.Method getter = e.getClass().getMethod(getterName);
    return getter.invoke(e);
}

public static void setFieldObject(Field f, Object e, Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
    //à modifier :
    String fieldName = f.getName();
    String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    Class<?> valueType = value.getClass();
    java.lang.reflect.Method setter = e.getClass().getMethod(setterName, valueType);
    setter.invoke(e, value);
}

 public static Vector<Field> getFieldRecursives(Class<?> clazz) {
        Vector<Field> fields = new Vector<>();
        Class<?> current = clazz;

        while (current != null) {
            if (current==Object.class) return fields;
            Field[] declaredFields = current.getDeclaredFields();
            for (Field f : declaredFields) {
                f.setAccessible(true);
                fields.add(f);
            }
            current = current.getSuperclass(); 
        }
        return fields;
    }
    public static Field getFieldRecursive(Class<?> clazz, String fieldName) throws NoSuchFieldException {
    Class<?> current = clazz;
    while (current != null) {
        try {
            Field field = current.getDeclaredField(fieldName);
           // field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            current = current.getSuperclass(); 
        }
    }
    throw new NoSuchFieldException("Field '" + fieldName + "' not found in class hierarchy of " + clazz.getName());
}
}
