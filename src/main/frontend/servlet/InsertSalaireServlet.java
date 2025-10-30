package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.backend.Metier;
import main.backend.base.Err.NoDataToUpdateErr;
import main.frontend.servlet.helper.ServletHelper;
import main.map.EMP;

public class InsertSalaireServlet extends HttpServlet{
    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out=resp.getWriter();
    resp.setContentType("text/plain");
    EMP newHisto=new EMP();
    Map<String, String[]> allParameters = req.getParameterMap();
    ServletHelper.FillSetable(allParameters, newHisto);
    try {
        Metier.insertNewHisto(out,newHisto,req.getParameter("DATE_SAL"));
    } catch (SQLException | ReflectiveOperationException | NoDataToUpdateErr e) {
        e.printStackTrace(out);
    }
   }
}
 