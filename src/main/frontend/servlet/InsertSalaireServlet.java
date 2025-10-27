package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.backend.BackEnd;
import main.base.Err.NoDataToUpdateErr;
import main.frontend.servlet.helper.ServletHelper;
import main.map.HISTOSAL;

public class InsertSalaireServlet extends HttpServlet{
    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out=resp.getWriter();
    resp.setContentType("text/plain");
    HISTOSAL newHisto=new HISTOSAL();
    Map<String, String[]> allParameters = req.getParameterMap();
    ServletHelper.FillSetable(allParameters, newHisto);
    try {
        BackEnd.insertNewHisto(out,newHisto);
    } catch (SQLException | ReflectiveOperationException | NoDataToUpdateErr e) {
        e.printStackTrace(out);
    }
   }
}
 