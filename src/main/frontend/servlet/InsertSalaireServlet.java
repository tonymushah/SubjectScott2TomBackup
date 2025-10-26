package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.base.Err.NoDataToUpdateErr;
import main.base.context.DBconnect;
import main.frontend.servlet.helper.ServletHelper;
import main.map.EMP;
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
        Connection conn=DBconnect.connect();
        EMP empwhere=new EMP();
        EMP empvalues=new EMP();
        empwhere.setEMPNO(newHisto.getEMPNO());
        empvalues.setSAL(newHisto.getMONTANT());

        Double empLastMontant=((EMP ) empwhere.findByConn(conn).elementAt(0)).getSAL();
        out.println(""+empvalues);
        out.println(""+empwhere);
        out.println(""+empLastMontant);

        out.println("Update EMP"+newHisto);
        empvalues.updateByConn(conn, empwhere);

        out.println("INSERT MONTANT "+empLastMontant);
        
        newHisto.setMONTANT(empLastMontant);
        newHisto.insertByConn(conn);
        conn.commit();
    } catch (SQLException | ReflectiveOperationException | NoDataToUpdateErr e) {
        e.printStackTrace();
    }
   }
}
 