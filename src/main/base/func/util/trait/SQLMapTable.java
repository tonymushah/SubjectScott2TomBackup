package main.base.func.util.trait;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import main.base.Err.NoDataToUpdateErr;
import main.base.func.sql.DBClassManager;

public interface SQLMapTable extends SQLMap {
    public default void insertByConn(Connection conn) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
        DBClassManager.insertObject0(conn,this);
    }
    public default void deleteByConn(Connection conn) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
        DBClassManager.deleteObject0(conn,this, this.getClass().getSimpleName());
    }
    public default void updateByConn(Connection conn,Object where) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
       DBClassManager.updateObject0(conn,this, where);
    }

    @Override
    public default Vector<Object> findByConn(Connection conn) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
        return DBClassManager.findObject0(conn,this,this.getClass().getSimpleName());
    }
    
}
