package debug;

import java.sql.Connection;
import java.util.Vector;

import main.base.context.DBconnect;

import main.map.V_SALAIRE_EMP_PROCHE;

public class Test2 {
    public static void main(String[] args) {
        // try {
        //     // Connexion à la base de données
        //     Connection conn = DBconnect.connect();
        //     // Date de test
        //     String date = "2025-10-31";

        //     // Table/Vue à utiliser


        //     // Objet WHERE de test

        //     V_SALAIRE_EMP_PROCHE where=new V_SALAIRE_EMP_PROCHE();
        // //   where.setFieldFromString("EMPNO","7902");
        //     // Appel de la fonctio
        //   //  PreparedStatement pstmt = SpecialQueryBuilder.getPstmtFor_SALAIRE_EMP(conn, date, tableName, where);

        //     // Exécution et affichage des résultats
        //    // ResultSet rs = pstmt.executeQuery();
        //     Vector<Object> lo=V_SALAIRE_EMP_PROCHE.findEmp(date,  where);
        //     for (Object object : lo) {
        //         System.out.println(""+object.toString()+"  "+((V_SALAIRE_EMP_PROCHE)object).getANCIEN_MONTANT());
        //     }

        //     // Fermeture des ressources
        //     //rs.close();
        //    // pstmt.close();
        //     conn.close();

        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }
}
