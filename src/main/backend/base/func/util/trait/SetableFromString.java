package main.backend.base.func.util.trait;

import java.util.Map;

import main.backend.base.Err.FromStringException;
import main.backend.base.func.util.FromStringManager;

public interface SetableFromString {
    public default void setFieldFromString(String name,String value) throws ReflectiveOperationException, FromStringException{
        FromStringManager.setFieldFromString(this,name,value);
    }
    public default void setFieldFromStringWithDeclaringClass(String name,String value) throws ReflectiveOperationException, FromStringException{
       FromStringManager.setFieldFromString(this,name,value);
    }

    public default String getFieldToString(String name) throws ReflectiveOperationException{
       return FromStringManager.getFieldToString(this, name);
    }
    public default void fillSetable(Map<String, String[]> allParameters ){
    for (Map.Entry<String, String[]> param : allParameters.entrySet()) {
        try {
            if(param.getValue()[0].isEmpty()){
                continue;
            }
            this.setFieldFromString(this.getClass().getSimpleName()+"."+param.getKey(),param.getValue()[0]);
        } catch (ReflectiveOperationException | FromStringException e) {
            continue;
        }
    }
   }}
