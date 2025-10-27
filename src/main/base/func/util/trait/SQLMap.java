package main.base.func.util.trait;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import main.base.Err.NoDataToUpdateErr;
import main.base.context.DBconnect;

public interface SQLMap {
    public Vector<Object> findByConn(Connection conn) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr;
    public default Vector<Object> find() throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
        Connection conn=DBconnect.connect();
        Vector<Object> retour=this.findByConn(conn);
        conn.close();
        return retour;
    };
}
