package main.backend.base.func.sql;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import main.backend.base.Err.NoDataToUpdateErr;
import main.backend.base.func.util.ReflectiveManager;

public class DBQueryManager {
    public static LinkedHashMap<String, Object> getColumnNotNull(Object eObject) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
             {
        if (eObject == null)
            return new LinkedHashMap<>();

        Class<?> eClass = eObject.getClass();
        LinkedHashMap<String, Object> columns = new LinkedHashMap<>();
        for (Field eField : ReflectiveManager.getFieldRecursives(eClass)) {
            Object valueField = ReflectiveManager.getFieldObject(eField, eObject);
            if (valueField == null) {
                continue;
            } else {
                columns.put(eField.getName(), valueField);
            }

        }
        return columns;
    }

    public static int fillpstmt0(PreparedStatement pstmt, LinkedHashMap<String, Object> nonNullColumns)
            throws SQLException {
        int index = 1;
       return fillpstmt0(pstmt, nonNullColumns,index);
    }
    public static int fillpstmt0(PreparedStatement pstmt, LinkedHashMap<String, Object> nonNullColumns,int index) throws SQLException{
         for (Object value : nonNullColumns.values()) {
            pstmt.setObject(index++, value);
        }
        
        return index;
    }

     public static void fillpstmtForUpdate(PreparedStatement pstmt,
            LinkedHashMap<String, Object> updateColumns,
            LinkedHashMap<String, Object> whereColumns)
            throws SQLException {
        int index = 1;

        for (Object value : updateColumns.values()) {
            pstmt.setObject(index++, value);
        }

        for (Object value : whereColumns.values()) {
            pstmt.setObject(index++, value);
        }
    }

    public static PreparedStatement getPstmtFind(Connection conn, String tableName, Object where)
            throws ReflectiveOperationException, SQLException {
        LinkedHashMap<String, Object> nonNullColumns = getColumnNotNull(where);

        PreparedStatement pstmt = conn.prepareStatement(DBStringManager.getQueryForFind(nonNullColumns, tableName));

        if (!nonNullColumns.isEmpty()) {
            fillpstmt0(pstmt, nonNullColumns);
        }
        return pstmt;
    }

    public static PreparedStatement getPstmtUpdate(Connection conn, String tableName, Object values, Object where)
            throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {

        LinkedHashMap<String, Object> updateColumns = getColumnNotNull(values);
        LinkedHashMap<String, Object> whereColumns = getColumnNotNull(where);
        if (updateColumns.isEmpty()) {
            throw new NoDataToUpdateErr("No column to update for " + tableName);
        }
        PreparedStatement pstmt = conn
                .prepareStatement(DBStringManager.getQueryForUpdate(updateColumns, whereColumns, tableName));

        fillpstmtForUpdate(pstmt, updateColumns, whereColumns);
        return pstmt;
    }

    public static PreparedStatement getPstmtInsert(Connection conn, Object value, String tableName)
            throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        LinkedHashMap<String, Object> insertColumns = getColumnNotNull(value);
        if (insertColumns.isEmpty()) {
            throw new NoDataToUpdateErr("No data to insert for " + tableName);
        }
        PreparedStatement pstmt = conn.prepareStatement(DBStringManager.getQueryForInsert(insertColumns, tableName));
        fillpstmt0(pstmt, insertColumns);
        return pstmt;
    }
    public static PreparedStatement getPstmtDelete(Connection conn, String tableName, Object where) throws SQLException, ReflectiveOperationException{
         LinkedHashMap<String, Object> nonNullColumns = getColumnNotNull(where);

        PreparedStatement pstmt = conn.prepareStatement(DBStringManager.getQueryForDelete(nonNullColumns, tableName));

        if (!nonNullColumns.isEmpty()) {
            fillpstmt0(pstmt, nonNullColumns);
        }
        return pstmt;
    }
}
