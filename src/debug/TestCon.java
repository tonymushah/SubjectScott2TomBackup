package debug;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

import main.backend.base.context.DBconnect;
/*
 * 
 * Just some testing if the connection actually works
 * 
 * @author TonyMushah
 */
public class TestCon {
    public static void main(String[] args) {
        try (Connection con = DBconnect.connect()) {
            DatabaseMetaData metaData = con.getMetaData();
            System.out.format("%s \n", metaData.getURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
