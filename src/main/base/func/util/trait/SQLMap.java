package main.base.func.util.trait;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import main.base.Err.NoDataToUpdateErr;

public interface SQLMap {
    public Vector<Object> find(Connection conn) throws SQLException, ReflectiveOperationException, NoDataToUpdateErr;
}
