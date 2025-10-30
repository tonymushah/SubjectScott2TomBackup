package main.backend.base.context;

import java.util.HashMap;

public class DBTypes {
    public static final HashMap<Class<?>, String> classDatable_ReadFunction=init_readHashMap();
    static HashMap<Class<?>, String> init_readHashMap() {
        HashMap<Class<?>, String> retour = new HashMap<>();
        retour.put(java.lang.String.class, "getString");
        retour.put(java.lang.Integer.class, "getInt");
        retour.put(java.lang.Boolean.class, "getBoolean");
        retour.put(java.lang.Long.class, "getLong");
        retour.put(java.lang.Double.class, "getDouble");
        retour.put(java.sql.Date.class, "getDate");
        return retour;
    }
}
