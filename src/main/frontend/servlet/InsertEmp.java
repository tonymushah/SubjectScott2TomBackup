package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.backend.Metier;
import main.common.map.EMP;
import main.frontend.servlet.helper.ServletHelper;

public class InsertEmp extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        EMP where = new EMP();
        ServletHelper.FillSetable(req.getParameterMap(), where);
        try {
            Metier.insertNewEmp(out, where);
            resp.sendRedirect("./page/inf.jsp");
        } catch (Exception e) {
            out.println("Error : "+e.getMessage());;
        }


    }
}
