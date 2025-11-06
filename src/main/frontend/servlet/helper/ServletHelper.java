package main.frontend.servlet.helper;

import java.util.Map;
import java.util.Vector;

import main.backend.base.Err.FromStringException;
import main.backend.base.func.util.trait.SetableFromString;
import main.frontend.display.TableBuilder;

public class ServletHelper {
    public static void FillSetable(Map<String, String[]> allParameters, SetableFromString object) {
        for (Map.Entry<String, String[]> param : allParameters.entrySet()) {
            try {
                if (param.getValue()[0].isEmpty()) {
                    continue;
                }
                object.setFieldFromString(param.getKey(), param.getValue()[0]);
            } catch (ReflectiveOperationException | FromStringException e) {
                continue;
            }
        }
    }

    public static <T> String generateContentSearch(Object where, Vector<T> results, String hrefForRow)
            throws ReflectiveOperationException {
        StringBuilder sb = new StringBuilder();

        sb.append("<div class=\"search-results\">");
        sb.append("<h2>Résultats de la recherche</h2>");
        sb.append("<div class=\"search-info\">");
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
            sb.append(TableBuilder.createTable(results, hrefForRow));
            sb.append("</div>");
        }

        sb.append("<div class=\"action-buttons\">");
        sb.append("</div>");
        sb.append("</div>");

        return sb.toString();
    }

    public static String generateErrorContentSearch(String date, Object where, Exception e) {
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
