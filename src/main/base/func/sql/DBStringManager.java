package main.base.func.sql;

import java.util.LinkedHashMap;

public class DBStringManager {
    public static String getWhereClause(LinkedHashMap<String, Object> nonNullColumns) {
        StringBuilder sqlBuilder = new StringBuilder();
        boolean first = true;
        for (String column : nonNullColumns.keySet()) {
            if (!first) {
                sqlBuilder.append(" AND ");
            } else {
                first = false;
            }
            sqlBuilder.append(column).append(" = ?");
        }
        return sqlBuilder.toString();
    }

    public static String getSetClause(LinkedHashMap<String, Object> updateColumns) {
        StringBuilder sqlBuilder = new StringBuilder();
        boolean first = true;
        for (String column : updateColumns.keySet()) {
            if (!first) {
                sqlBuilder.append(", ");
            } else {
                first = false;
            }
            sqlBuilder.append(column).append(" = ?");
        }
        return sqlBuilder.toString();
    }
    public static String getQueryForInsert(LinkedHashMap<String, Object> insertColumns, String tableName) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
        StringBuilder columnsBuilder = new StringBuilder();
        StringBuilder valuesBuilder = new StringBuilder();

        boolean first = true;
        for (String column : insertColumns.keySet()) {
            if (!first) {
                columnsBuilder.append(", ");
                valuesBuilder.append(", ");
            } else {
                first = false;
            }
            columnsBuilder.append(column);
            valuesBuilder.append("?");
        }
        sqlBuilder.append(columnsBuilder)
                .append(") VALUES (")
                .append(valuesBuilder)
                .append(")");
        return sqlBuilder.toString();
    }
    public static String getQueryForFind(LinkedHashMap<String,Object> nonNullColumns,String tableName){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM ").append(tableName);
        if (!nonNullColumns.isEmpty()) {
            sqlBuilder.append(" WHERE ").append(DBStringManager.getWhereClause(nonNullColumns));
        }
        return sqlBuilder.toString();
    }
    public static String getQueryForUpdate(LinkedHashMap<String, Object> updateColumns,LinkedHashMap<String, Object> whereColumns,String tableName){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("UPDATE ").append(tableName).append(" SET ");
        sqlBuilder.append(DBStringManager.getSetClause(updateColumns));
        if (!whereColumns.isEmpty()) {
            sqlBuilder.append(" WHERE ").append(DBStringManager.getWhereClause(whereColumns));
        }
        return sqlBuilder.toString();
    }
    public static String getQueryForDelete(LinkedHashMap<String,Object> nonNullColumns,String tableName){
         StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("DELETE FROM ").append(tableName);
        if (!nonNullColumns.isEmpty()) {
            sqlBuilder.append(" WHERE ").append(DBStringManager.getWhereClause(nonNullColumns));
        }
        return sqlBuilder.toString();
    }
}
