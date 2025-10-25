package main.base.func.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

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
}
