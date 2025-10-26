package main.frontend.servlet.helper;

import java.util.Map;

import main.base.Err.FromStringException;
import main.base.func.util.trait.SetableFromString;

public class ServletHelper {
    public static void FillSetable(Map<String, String[]> allParameters,SetableFromString object ){
    for (Map.Entry<String, String[]> param : allParameters.entrySet()) {
        try {
            if(param.getValue()[0].isEmpty()){
                continue;
            }
            object.setFieldFromString(param.getKey(),param.getValue()[0]);
        } catch (ReflectiveOperationException | FromStringException e) {
            continue;
        }
    }
    }
}
