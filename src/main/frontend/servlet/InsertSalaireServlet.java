package main.frontend.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.base.Err.FromStringException;
import main.base.Err.NoDataToUpdateErr;
import main.base.context.DBconnect;
import main.map.HISTOSAL;

public class InsertSalaireServlet extends HttpServlet{
    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
   HISTOSAL newHisto=new HISTOSAL();
   Map<String, String[]> allParameters = req.getParameterMap();
    for (Map.Entry<String, String[]> param : allParameters.entrySet()) {
        try {
            newHisto.setFieldFromString(param.getKey(),param.getValue()[0]);
        } catch (ReflectiveOperationException | FromStringException e) {
            throw new ServletException(e);
        }
    }
    try {
        newHisto.insertByConn(DBconnect.connect());
    } catch (SQLException | ReflectiveOperationException | NoDataToUpdateErr e) {

        e.printStackTrace();
    }
   }
}
 