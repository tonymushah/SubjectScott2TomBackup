package main.backend.base.func.special;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import main.backend.base.annotation.IdDropDown;
import main.backend.base.context.DBconnect;
import main.backend.base.func.sql.DBQueryManager;
import main.backend.base.func.sql.DBStringManager;
import main.backend.base.func.util.FromStringManager;
import main.map.V_SALAIRE_DEPT_PROCHE;

public class SpecialQueryBuilder {
    public static boolean isGeDateToNow(String dateString) throws SQLException {
        String query = "SELECT 1 FROM dual WHERE TRUNC(?) >= TRUNC(SYSDATE)";

        try (PreparedStatement pstmt = DBconnect.connect().prepareStatement(query)) {
            pstmt.setDate(1, FromStringManager.parseDate(dateString));
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public static void resIntoHashMap(HashMap<String, String> retour, ResultSet rs) throws SQLException {
        if (retour == null)
            retour = new HashMap<>();
        while (rs.next()) {
            String id = "" + rs.getObject(1);
            String name = "" + rs.getString(2);
            retour.put(id, name);
        }

    }

    public static void freeRqst(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        rs.close();
        ps.close();
        conn.close();
    }

    public static LinkedHashMap<String, String> getNameSelectable(Field f) throws SQLException {
        LinkedHashMap<String, String> retour = new LinkedHashMap<>();
        IdDropDown inf = f.getAnnotation(IdDropDown.class);
        String rqst = "SELECT " + inf.foreingkey() + ", " + inf.todisplay()
                + " FROM " + inf.classtoJoin().getSimpleName() + " ORDER BY " + inf.foreingkey();

        System.out.println("Requête exécuté: " + rqst);
        Connection conn = DBconnect.connect();
        PreparedStatement ps = conn.prepareStatement(rqst);
        ResultSet rs = ps.executeQuery();
        resIntoHashMap(retour, rs);
        freeRqst(conn, ps, rs);
        return retour;
    }

    public static PreparedStatement getPstmtFor_SALAIRE_EMP(Connection conn, String date, String tableName,
            Object where) throws SQLException, ReflectiveOperationException {
        LinkedHashMap<String, Object> lh = DBQueryManager.getColumnNotNull(where);
        String s = DBStringManager.getWhereClause(lh);
        String cond = !s.isEmpty() ? s : " 1=1 ";

        String rqst = "SELECT EMPNO, ENAME, ANCIEN_MONTANT AS SALAIRE_A_PAYER, DATE_SAL\n" +
                "FROM " + tableName + "\n" +
                "WHERE (EMPNO, DATE_SAL) IN (\n" +
                "    SELECT EMPNO, MIN(DATE_SAL)\n" +
                "    FROM " + tableName + "\n" +
                "    WHERE DATE_SAL >= ? \n" +
                "    GROUP BY EMPNO\n" +
                ")\n" +
                "AND ? >= HIREDATE\n" +
                "AND " + cond + "\n" +
                "\n" +
                "UNION ALL\n" +
                "\n" +
                "SELECT EMPNO, ENAME, SAL AS SALAIRE_A_PAYER, HIREDATE AS DATE_SAL\n" +
                "FROM " + tableName + "\n" +
                "WHERE NOT EXISTS (\n" +
                "    SELECT 1 FROM " + tableName + " v2\n" +
                "    WHERE v2.EMPNO = " + tableName + ".EMPNO\n" +
                "    AND v2.DATE_SAL >= ?\n" +
                ")\n" +
                "AND ? >= HIREDATE\n" +
                "AND " + cond + "\n" +
                "ORDER BY EMPNO ASC";

        PreparedStatement pStatement = conn.prepareStatement(rqst);
        int index = 1;

        pStatement.setDate(index++, FromStringManager.parseDate(date));
        pStatement.setDate(index++, FromStringManager.parseDate(date));
        index = DBQueryManager.fillpstmt0(pStatement, lh, index);
        pStatement.setDate(index++, FromStringManager.parseDate(date));
        pStatement.setDate(index++, FromStringManager.parseDate(date));

        DBQueryManager.fillpstmt0(pStatement, lh, index);
        return pStatement;
    }

    public static PreparedStatement getPstmtFor_SALAIRE_DEPT(Connection conn, String date, String tableName,
            V_SALAIRE_DEPT_PROCHE where) throws SQLException, ReflectiveOperationException {
        LinkedHashMap<String, Object> lh = DBQueryManager.getColumnNotNull(where);
        String s = DBStringManager.getWhereClauseByTableName(lh, "d");
        String cond = !s.isEmpty() ? s : " 1=1 ";

        String rqst = "SELECT \n" +
                "    d.DEPTNO,\n" +
                "    d.DNAME,\n" +
                "    COALESCE(SUM(s.SALAIRE_A_PAYER), 0) AS SALAIRE_A_PAYER,\n" +
                "    COUNT(s.EMPNO) AS NOMBRE_EMP\n" +
                "FROM dept d\n" +
                "LEFT JOIN (\n" +
                "    SELECT DEPTNO, EMPNO, ANCIEN_MONTANT AS SALAIRE_A_PAYER\n" +
                "    FROM " + tableName + "\n" +
                "    WHERE (EMPNO, DATE_SAL) IN (\n" +
                "        SELECT EMPNO, MIN(DATE_SAL)\n" +
                "        FROM " + tableName + "\n" +
                "        WHERE DATE_SAL >= ? \n" +
                "        GROUP BY EMPNO\n" +
                "    )\n" +
                "    AND ? >= HIREDATE\n" +
                "    \n" +
                "    UNION ALL\n" +
                "    \n" +
                "    SELECT DEPTNO, EMPNO, SAL AS SALAIRE_A_PAYER\n" +
                "    FROM " + tableName + "\n" +
                "    WHERE NOT EXISTS (\n" +
                "        SELECT 1 FROM " + tableName + " v2\n" +
                "        WHERE v2.EMPNO = " + tableName + ".EMPNO\n" +
                "        AND v2.DATE_SAL >= ?\n" +
                "    )\n" +
                "    AND ? >= HIREDATE\n" +
                ") s ON d.DEPTNO = s.DEPTNO\n" +
                "WHERE " + cond + "\n" +
                "GROUP BY d.DEPTNO, d.DNAME\n" +
                "ORDER BY d.DEPTNO ASC";

        System.out.println(rqst);
        PreparedStatement pStatement = conn.prepareStatement(rqst);
        int index = 1;
        pStatement.setDate(index++, FromStringManager.parseDate(date));
        pStatement.setDate(index++, FromStringManager.parseDate(date));
        pStatement.setDate(index++, FromStringManager.parseDate(date));
        pStatement.setDate(index++, FromStringManager.parseDate(date));
        DBQueryManager.fillpstmt0(pStatement, lh, index);

        return pStatement;
    }
}
