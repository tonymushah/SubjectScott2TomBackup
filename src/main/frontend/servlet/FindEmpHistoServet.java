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
        String date=req.getParameter("DATE-REQUIRED").trim();
        V_SALAIRE_EMP_PROCHE where=new V_SALAIRE_EMP_PROCHE();
        ServletHelper.FillSetable(req.getParameterMap(), where);
       //where.setEMPNO(7934);
        try {
            out.println(""+date);
            out.println(""+where);
            Vector<Object> lo=V_SALAIRE_EMP_PROCHE.findEmp(date,where);

            if(lo.isEmpty()){
               out.println("empty");
            }else{
            out.println(""+TableBuilder.createTable(lo));
            }
        } catch (ReflectiveOperationException |SQLException e) {
           e.printStackTrace(out) ;
        } 

   }   
}
