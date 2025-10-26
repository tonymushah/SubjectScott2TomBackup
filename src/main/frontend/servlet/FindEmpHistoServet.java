package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.frontend.display.TableBuilder;
import main.frontend.servlet.helper.ServletHelper;
import main.map.V_SALAIRE_EMP_PROCHE;


public class FindEmpHistoServet extends HttpServlet{
        @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        String date=req.getParameter("DATE-REQUIRED");
        V_SALAIRE_EMP_PROCHE se=new V_SALAIRE_EMP_PROCHE();
        ServletHelper.FillSetable(req.getParameterMap(), se);
        out.println("EMPNO : "+se.getEMPNO());
        try {
            Vector<Object> lo=se.findEmp(date);
            if(lo.isEmpty()){
                
            }else{
            out.println(""+TableBuilder.createTable(lo));
            }
        } catch (ReflectiveOperationException |SQLException e) {
           e.printStackTrace(out) ;
        } 

   }   
}
