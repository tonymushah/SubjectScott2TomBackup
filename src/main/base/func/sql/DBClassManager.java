package main.base.func.sql;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import main.base.Err.NoDataToUpdateErr;
import main.base.context.DBTypes;
import main.base.func.util.ReflectiveManager;

public class DBClassManager {
    public static void UpdateObject0(Connection conn, Object updates, Object where)
            throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        Class<?> clazz = updates.getClass();
        PreparedStatement pStatement = DBQueryManager.getPstmtUpdate(conn, clazz.getSimpleName(), updates, where);
        pStatement.executeUpdate();
        pStatement.close();
    }

    public static void InsertObject0(Connection conn, Object value)
            throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        Class<?> clazz = value.getClass();
        PreparedStatement pStatement = DBQueryManager.getPstmtInsert(conn, value, clazz.getSimpleName());
        pStatement.executeQuery();
        pStatement.close();
    }

    public static Vector<Object> findObject0(Connection conn, Object where, String tableName)
            throws SQLException, ReflectiveOperationException {
        Vector<Object> results = new Vector<>();
        PreparedStatement pStatement = DBQueryManager.getPstmtFind(conn, tableName, where);
        ResultSet rs = pStatement.executeQuery();
        Class<?> clazz = where.getClass();
        while (rs.next()) {
            Object map = mapResultSetToObject(rs, clazz);
            results.add(map);
        }
        pStatement.close();
        return results;
    }

    public static void DeleteObject0(Connection conn, Object where, String tableName)
            throws SQLException, ReflectiveOperationException {
        PreparedStatement pStatement = DBQueryManager.getPstmtDelete(conn, tableName, where);
        pStatement.executeUpdate();
        pStatement.close();
    }

    public static Object mapResultSetToObject(ResultSet rs, Class<?> clazz)
            throws ReflectiveOperationException, SQLException {
        Object map = clazz.getDeclaredConstructor().newInstance();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i).toUpperCase();
            Field field = clazz.getDeclaredField(columnName);
            handleAppropriateGetField(map, rs, field, i);
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