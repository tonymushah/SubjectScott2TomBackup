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
import main.frontend.display.TableBuilder;
import main.frontend.servlet.helper.ServletHelper;
import main.map.V_SALAIRE_EMP_PROCHE;

public class FindEmpHistoServet extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String date = req.getParameter("DATE-REQUIRED") != null ? req.getParameter("DATE-REQUIRED").trim() : "";
        V_SALAIRE_EMP_PROCHE where = new V_SALAIRE_EMP_PROCHE();
        ServletHelper.FillSetable(req.getParameterMap(), where);
        
        try {
            Vector<Object> lo = V_SALAIRE_EMP_PROCHE.findEmp(date, where);
            String content = generateContent(date, where, lo);
            out.println(BodyBuilder.makeDefaultBody(content));
            
        } catch (ReflectiveOperationException | SQLException e) {
            String errorContent = generateErrorContent(date, where, e);
            out.println(BodyBuilder.makeDefaultBody(errorContent));
        } 
    }
    
    private String generateContent(String date, V_SALAIRE_EMP_PROCHE where, Vector<Object> results) throws ReflectiveOperationException {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<div class=\"search-results\">");
        sb.append("<h2>Résultats de la recherche</h2>");
        sb.append("<div class=\"search-info\">");
        sb.append("<p><strong>Date de référence:</strong> ").append(date).append("</p>");
        sb.append("<p><strong>Filtres appliqués:</strong> ").append(where.toString()).append("</p>");
        sb.append("</div>");
        
        if (results.isEmpty()) {
            sb.append("<div class=\"alert alert-info\">");
            sb.append("<h3>Aucun résultat trouvé</h3>");
            sb.append("<p>Aucun employé ne correspond aux critères de recherche.</p>");
            sb.append("</div>");
        } else {
            sb.append("<div class=\"results-count\">");
            sb.append("<p><strong>").append(results.size()).append(" résultat(s) trouvé(s)</strong></p>");
            sb.append("</div>");
            sb.append("<div class=\"table-container\">");
            sb.append(TableBuilder.createTable(results));
            sb.append("</div>");
        }
        
        sb.append("<div class=\"action-buttons\">");
        sb.append("<a href=\"./page/page2.jsp\" class=\"btn btn-primary\">Nouvelle recherche</a>");
        sb.append("</div>");
        sb.append("</div>");
        
        return sb.toString();
    }
    
    private String generateErrorContent(String date, V_SALAIRE_EMP_PROCHE where, Exception e) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("<div class=\"error-container\">");
        sb.append("<h2 class=\"error-title\">Erreur lors de la recherche</h2>");
        sb.append("<div class=\"error-details\">");
        sb.append("<p><strong>Date:</strong> ").append(date).append("</p>");
        sb.append("<p><strong>Filtres:</strong> ").append(where.toString()).append("</p>");
        sb.append("</div>");
        sb.append("<div class=\"alert alert-danger\">");
        sb.append("<h4>Message d'erreur:</h4>");
        sb.append("<pre>").append(e.getMessage()).append("</pre>");
        sb.append("</div>");
        sb.append("<div class=\"action-buttons\">");
        sb.append("<a href=\"../page/rechercheEmp.jsp\" class=\"btn btn-secondary\">Retour</a>");
        sb.append("</div>");
        sb.append("</div>");
        
        return sb.toString();
    }
}