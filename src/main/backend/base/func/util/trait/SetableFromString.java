package main.backend.base.func.util.trait;

import main.backend.base.Err.FromStringException;
import main.backend.base.func.util.FromStringManager;

public interface SetableFromString {
    public default void setFieldFromString(String name,String value) throws ReflectiveOperationException, FromStringException{
        FromStringManager.setFieldFromString(this,name,value);
    }
    public default String getFieldToString(String name) throws ReflectiveOperationException{
       return FromStringManager.getFieldToString(this, name);
    }
}
