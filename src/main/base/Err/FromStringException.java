package main.base.Err;

import java.lang.reflect.Field;

public class FromStringException extends Exception{
    public FromStringException(String message) {
        super(message);
    }
    public FromStringException(String message, Throwable cause) {
        super(message, cause);
    }
    public FromStringException(Throwable cause) {
        super(cause);
    }
    public FromStringException(Class<?> clazz) {
        super(clazz.getName()+" : impossible to use fromString ");
    }
    public FromStringException(Class<?> clazz,Throwable cause) {
        super(clazz.getName()+" : impossible to use fromString ",cause);
    }
    public FromStringException(Field field,Throwable cause) {
        super(field + " : impossible to use fromString ",cause);
    }
}
