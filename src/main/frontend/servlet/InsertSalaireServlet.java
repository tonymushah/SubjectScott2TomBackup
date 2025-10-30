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

public class InsertSalaireServlet extends HttpServlet{
    @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out=resp.getWriter();
    resp.setContentType("text/plain");
    Map<String, String[]> allParameters = req.getParameterMap();
    try {
        Metier.insertNewHisto(out,allParameters);
    } catch (SQLException | ReflectiveOperationException e) {
        e.printStackTrace(out);
    }
   }
}
 