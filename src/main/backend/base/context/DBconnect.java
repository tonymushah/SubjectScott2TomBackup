package main.backend.base.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnect {
    public final static String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
    public final static String user = "scott";
    public final static String password = "tiger";

    
    public static Connection connect() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        return conn;
    }
}
