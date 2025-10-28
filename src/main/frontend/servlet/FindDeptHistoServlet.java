package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.frontend.display.BodyBuilder;
import main.frontend.servlet.helper.ServletHelper;
import main.map.V_SALAIRE_DEPT_PROCHE;

public class FindDeptHistoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String date = req.getParameter("DATE-REQUIRED") != null ? req.getParameter("DATE-REQUIRED").trim() : "";
        V_SALAIRE_DEPT_PROCHE where = new V_SALAIRE_DEPT_PROCHE();
        ServletHelper.FillSetable(req.getParameterMap(), where);
        try {
            Vector<Object> lo = V_SALAIRE_DEPT_PROCHE.findDept(date,where);
            String content = ServletHelper.generateContent(date, where , lo);
            out.println(BodyBuilder.makeDefaultBody(content));
            
        } catch (ReflectiveOperationException | SQLException e) {
            String errorContent = ServletHelper.generateErrorContent(date, where, e);
            out.println(BodyBuilder.makeDefaultBody(errorContent));
        } 
    }
}
