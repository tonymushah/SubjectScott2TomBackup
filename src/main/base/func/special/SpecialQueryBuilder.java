package main.base.func.special;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.base.func.util.FromStringManager;

public class SpecialQueryBuilder {
    public static PreparedStatement getPstmtFor_SALAIRE_EMP(Connection conn,String date,String tableName) throws SQLException{
        //vérification s'il a date<la date d'embauche => on n'afficher pas
        //vérification s'il la personne n'est pas dans histosal => on prend ses inforamtion dans la table emp 
        
        PreparedStatement pStatement=conn.prepareStatement("SELECT *\n" + //
                        "FROM " +tableName+"\n"+ //
                        "WHERE DATE_SAL = (\n" + //
                        "    SELECT MAX(h2.DATE_SAL)\n" + //
                        "    FROM histosal h2\n" + //
                        "    WHERE h2.EMPNO = "+tableName+".EMPNO\n" + //
                        "      AND h2.DATE_SAL <= ?" + //
                        ")\n");
        pStatement.setDate(1, FromStringManager.parseDate(date));
        return pStatement;
    }
}
