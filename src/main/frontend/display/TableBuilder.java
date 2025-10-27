package main.frontend.display;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import main.base.func.util.ReflectiveManager;

public class TableBuilder {
    
    public static String createRow0(Class<?> clazz, Object obj, int rowIndex) 
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            Object fObject = ReflectiveManager.getFieldObject(f, obj);
            String cellClass = "align-middle";
            
            // Appliquer des classes conditionnelles selon le type de données
            if (fObject instanceof Number) {
                cellClass += " text-end";
            } else if (fObject instanceof Boolean) {
                cellClass += " text-center";
            }
            
            sb.append("<td class=\"").append(cellClass).append("\">")
              .append(formatCellValue(fObject))
              .append("</td>");
        }
        return sb.toString();
    }
    
    public static String createRow(Class<?> clazz, Object obj, int rowIndex) 
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        
        // Alternance des couleurs de ligne pour meilleure lisibilité
        String rowClass = (rowIndex % 2 == 0) ? "table-primary" : "table-light";
        sb.append("<tr class=\"").append(rowClass).append("\">");
        sb.append(createRow0(clazz, obj, rowIndex));
        sb.append("</tr>");
        return sb.toString();
    }

    public static String createHeader0(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            String fieldName = FieldHelper.getNameHTML(f);
            String headerClass = "align-middle bg-dark text-white";
            
            // Ajouter des icônes ou indications selon le type de champ
            if (FieldHelper.isNumericType(f.getType())) {
                headerClass += " text-end";
            } else {
                headerClass += " text-start";
            }
            
            sb.append("<th class=\"").append(headerClass).append("\">")
              .append("<span class=\"fw-bold\">").append(fieldName).append("</span>")
              .append("</th>");
        }
        return sb.toString();
    }

    public static String createHeader(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("<thead class=\"table-dark\">")
          .append("<tr>").append(createHeader0(clazz)).append("</tr>")
          .append("</thead>");
        return sb.toString();
    }
    
    public static String createTable(Vector<Object> lObjects) throws ReflectiveOperationException {
        if (lObjects == null || lObjects.isEmpty()) {
            return createEmptyTable();
        }
        
        StringBuilder sb = new StringBuilder();
        Class<?> clazz = lObjects.get(0).getClass();
        

        sb.append("<div class=\"table-responsive\">");
        sb.append("<table class=\"table table-striped table-hover table-bordered\">");
        
        sb.append(createHeader(clazz));
        
        sb.append("<tbody>");
        for (int i = 0; i < lObjects.size(); i++) {
            Object iter = lObjects.get(i);
            sb.append(createRow(clazz, iter, i));   
        }
        sb.append("</tbody>");
        
        sb.append("</table>");
        sb.append("</div>");
        
        sb.append("<div class=\"mt-2 text-muted small\">")
          .append("<i class=\"bi bi-info-circle\"></i> ")
          .append(lObjects.size()).append(" élément(s) affiché(s)")
          .append("</div>");
        
        return sb.toString();
    }
    
    public static String createTable(Vector<Object> lObjects, String caption) throws ReflectiveOperationException {
        String table = createTable(lObjects);
        
        // Remplacer la balise table pour ajouter une légende
        if (table.contains("<table")) {
            table = table.replaceFirst("<table", 
                "<table class=\"table table-striped table-hover table-bordered\">" +
                "<caption class=\"caption-top fw-bold bg-light p-2\">" + caption + "</caption>");
        }
        
        return table;
    }
    
    private static String createEmptyTable() {
        return "<div class=\"alert alert-info text-center\">" +
               "<i class=\"bi bi-table\"></i> " +
               "Aucune donnée à afficher" +
               "</div>";
    }
    
private static String formatCellValue(Object value) {
    if (value == null) {
        return "<span class=\"text-muted fst-italic\">null</span>";
    }
    
    String stringValue = value.toString();
    

    if (stringValue.length() > 50) {
        return "<span title=\"" + escapeHtml(stringValue) + "\">" 
             + escapeHtml(stringValue.substring(0, 47)) + "...</span>";
    }
    return escapeHtml(stringValue);
}
    private static String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                  .replace("<", "&lt;")
                  .replace(">", "&gt;")
                  .replace("\"", "&quot;")
                  .replace("'", "&#39;");
    }
}