package main.base.func.special;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import main.base.context.DBconnect;
import main.base.func.sql.DBQueryManager;
import main.base.func.sql.DBStringManager;
import main.base.func.util.FromStringManager;

public class SpecialQueryBuilder {
    //vérification s'il a date<la date d'embauche => on n'afficher pas
        //vérification s'il la personne n'est pas dans histosal => on prend ses inforamtion dans la table emp 

    public static boolean isGeDateToNow(String dateString) throws SQLException {
    String query = "SELECT 1 FROM dual WHERE TRUNC(?) >= TRUNC(SYSDATE)";
    
    try (PreparedStatement pstmt = DBconnect.connect().prepareStatement(query)) {
        pstmt.setDate(1,FromStringManager.parseDate(query));
        try (ResultSet rs = pstmt.executeQuery()) {
            return rs.next(); 
        }
    }
    }

   public static PreparedStatement getPstmtFor_SALAIRE_EMP(Connection conn, String date, String tableName,Object where) throws SQLException,ReflectiveOperationException{
    LinkedHashMap<String,Object> lh=DBQueryManager.getColumnNotNull(where);
   String s = DBStringManager.getWhereClause(lh);
   String cond=!s.isEmpty() ? s : " 1=1 ";
    PreparedStatement pStatement = conn.prepareStatement(
        "SELECT *\n" +
        "FROM " + tableName + "\n" +
        "WHERE (EMPNO, DATE_SAL) IN (\n" +
        "    SELECT EMPNO, MAX(DATE_SAL)\n" +
        "    FROM " + tableName + "\n" +
        "    WHERE DATE_SAL <= ? \n" +
        "    GROUP BY EMPNO\n" +
        ") AND " +cond  +"\n"+
        "ORDER BY EMPNO ASC"
    );
    int index=1;
    pStatement.setDate(index++, FromStringManager.parseDate(date));
    DBQueryManager.fillpstmt0(pStatement,lh,index);
    return pStatement;
}
}


