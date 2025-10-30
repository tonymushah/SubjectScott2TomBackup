package main.backend.base.func.util;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import main.backend.base.Err.FromStringException;

public class FromStringManager {
    public static String getNaiveNameField(String fieldWithDelcaringClass) {
        return fieldWithDelcaringClass.substring(fieldWithDelcaringClass.lastIndexOf('.') + 1);
    }

    public static Date parseDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(value, formatter);
        return Date.valueOf(localDate);
    }

    public static Object fromString(String value, Class<?> clazz) throws FromStringException {
        if (value == null)
            return null;

        try {
            if (clazz == int.class || clazz == Integer.class) {
                return Integer.parseInt(value);
            } else if (clazz == long.class || clazz == Long.class) {
                return Long.parseLong(value);
            } else if (clazz == double.class || clazz == Double.class) {
                return Double.parseDouble(value);
            } else if (clazz == float.class || clazz == Float.class) {
                return Float.parseFloat(value);
            } else if (clazz == boolean.class || clazz == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (clazz == char.class || clazz == Character.class) {
                return value.length() > 0 ? value.charAt(0) : '\0';
            } else if (clazz == String.class) {
                return value;
            } else if (clazz == Date.class) {
                return parseDate(value);
            } else {
                throw new FromStringException(clazz);
            }
        } catch (Exception e) {
            throw new FromStringException(clazz, e);
        }
    }

    public static void setFieldFromString(Object e, String name, String value)
            throws ReflectiveOperationException, FromStringException {
        Class<?> clazz = e.getClass();
        Field f = ReflectiveManager.getFieldRecursive(clazz, getNaiveNameField(name));
        Object valueObject = fromString(value, f.getType());
        ReflectiveManager.setFieldObject(f, e, valueObject);
    }

    public static String getFieldToString(Object e, String name) throws ReflectiveOperationException {
        Class<?> clazz = e.getClass();
        Field f = ReflectiveManager.getFieldRecursive(clazz, getNaiveNameField(name));
        Object field = ReflectiveManager.getFieldObject(f, e);
        if (f.getType() == Date.class) {
            LocalDate localDate = ((Date)(field)).toLocalDate(); 
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return localDate.format(formatter);
        } else {
            return field.toString();
        }

    }
}
