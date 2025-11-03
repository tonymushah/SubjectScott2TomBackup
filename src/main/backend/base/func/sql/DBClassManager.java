package main.backend.base.func.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import main.backend.base.Err.NoDataToUpdateErr;
import main.backend.base.context.DBTypes;
import main.backend.base.func.util.ReflectiveManager;

public class DBClassManager {
    public static void updateObject0(Connection conn, Object updates, Object where)
            throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        Class<?> clazz = updates.getClass();
        PreparedStatement pStatement = DBQueryManager.getPstmtUpdate(conn, clazz.getSimpleName(), updates, where);
        pStatement.executeUpdate();
        pStatement.close();
    }

    public static void insertObject0(Connection conn, Object value)
            throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        Class<?> clazz = value.getClass();
        PreparedStatement pStatement = DBQueryManager.getPstmtInsert(conn, value, clazz.getSimpleName());
        pStatement.executeQuery();
        pStatement.close();
    }

    public static <T> Vector<T> RsIntoVector(ResultSet rs, Class<T> clazz)
            throws ReflectiveOperationException, SQLException {
        Vector<T> results = new Vector<T>();
        while (rs.next()) {
            T map = mapResultSetToObject(rs, clazz);
            results.add(map);
        }
        return results;
    }

    public static <T> Vector<T> findObject0(PreparedStatement pStatement, Class<T> clazz)
            throws SQLException, ReflectiveOperationException {
        ResultSet rs = pStatement.executeQuery();
        Vector<T> retour = RsIntoVector(rs, clazz);
        rs.close();
        pStatement.close();
        return retour;
    }

    public static <T extends Object> Vector<T> findObject0(Connection conn, T where, String tableName)
            throws SQLException, ReflectiveOperationException {
        PreparedStatement pStatement = DBQueryManager.getPstmtFind(conn, tableName, where);

        Class<T> whereClass = (Class<T>) where.getClass();
        return findObject0(pStatement, whereClass);
    }

    public static <T> Vector<T> findObjectByRetour(Connection conn, Object where, Class<T> retour, String tableName)
            throws SQLException, ReflectiveOperationException {
        PreparedStatement pStatement = DBQueryManager.getPstmtFind(conn, tableName, where);
        return findObject0(pStatement, retour);
    }

    public static void deleteObject0(Connection conn, Object where, String tableName)
            throws SQLException, ReflectiveOperationException {
        PreparedStatement pStatement = DBQueryManager.getPstmtDelete(conn, tableName, where);
        pStatement.executeUpdate();
        pStatement.close();
    }

    public static <T> T mapResultSetToObject(ResultSet rs, Class<T> clazz)
            throws ReflectiveOperationException, SQLException {
        System.out.println("" + clazz);
        T map = clazz.getDeclaredConstructor().newInstance();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i).toUpperCase();
            try {
                Field field = ReflectiveManager.getFieldRecursive(clazz, columnName);
                handleAppropriateGetField(map, rs, field, i);
            } catch (Exception e) {
                continue;
            }

        }
        return map;
    }

    public static void handleAppropriateGetField(Object map, ResultSet rs, Field field, int index)
            throws ReflectiveOperationException, SQLException {
        Class<?> fieldType = field.getType();

        java.lang.Character a = fieldType.getSimpleName().charAt(0);
        String readfnName = java.lang.Character.isLowerCase(a)
                ? "get" + java.lang.Character.toUpperCase(a) + fieldType.getName().substring(1)
                : DBTypes.classDatable_ReadFunction.get(fieldType);
        invokeReadFn(rs, readfnName, field, map, index);

    }

    public static void invokeReadFn(ResultSet rs, String readfnName, Field f, Object map, int index)
            throws ReflectiveOperationException, SQLException {
        Method m = ResultSet.class.getDeclaredMethod(readfnName, int.class);
        Object value = m.invoke(rs, index);
        if (rs.wasNull())
            return;
        ReflectiveManager.setFieldObject(f, map, value);
    }
}