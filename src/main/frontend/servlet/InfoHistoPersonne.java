package main.frontend.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.common.map.EMP;
import main.common.map.HISTOSAL;
import main.common.map.V_SALAIRE_EMP_PROCHE;
import main.frontend.display.BodyBuilder;
import main.frontend.display.TableBuilder;
import main.frontend.servlet.helper.ServletHelper;

public class InfoHistoPersonne extends HttpServlet {
     @Override
     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          PrintWriter out = resp.getWriter();
          resp.setContentType("text/html");
          V_SALAIRE_EMP_PROCHE histo_gotten = new V_SALAIRE_EMP_PROCHE();
          Map<String, String[]> allParameters = req.getParameterMap();
          ServletHelper.FillSetable(allParameters, histo_gotten);

          HISTOSAL where = new HISTOSAL();
          where.setEMPNO(histo_gotten.getEMPNO());

          EMP infopersonne = new EMP();
          infopersonne.setEMPNO(histo_gotten.getEMPNO());
          try {
               Vector<Object> l_info = infopersonne.find();
               Vector<Object> lo = where.find();

               
               StringBuilder sb=new StringBuilder("<h1>Info de la Personne : </h1>");
               sb.append(TableBuilder.createTable(l_info, "#"));
               sb.append("<h1>Historique de la personne : </h1>");
               sb.append(TableBuilder.createTable(lo, "#"));
               out.println(BodyBuilder.makeDefaultBody(sb.toString()));

          } catch (ReflectiveOperationException | SQLException e) {
               out.println(e.getMessage());
          }

     }
}
