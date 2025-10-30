package main.backend.base.func.util.trait;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import main.backend.base.context.DBconnect;
import main.backend.base.func.sql.DBClassManager;

public interface SQLMap {
    public default Vector<Object> findByConn(Connection conn) throws SQLException, ReflectiveOperationException{ 
        return DBClassManager.findObject0(conn,this,this.getClass().getSimpleName());
    };
    public default Vector<Object> find() throws SQLException, ReflectiveOperationException{
        Connection conn=DBconnect.connect();
        Vector<Object> retour=this.findByConn(conn);
        conn.close();
        return retour;
    };
}
