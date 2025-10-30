package main.frontend.display;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import main.backend.base.func.util.ReflectiveManager;

public class TableBuilder {

    public static String createRow0(Class<?> clazz, Object obj, int rowIndex, String hrefForRow)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        String urlParams = buildUrlParams(clazz, obj);
        String rowClass = getRowClass(rowIndex);

        StringBuilder sb = new StringBuilder();
        sb.append("<tr class=\"").append(rowClass).append("\">");

        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            sb.append(createCell(f, obj, hrefForRow, urlParams));
        }

        sb.append("</tr>");
        return sb.toString();
    }

    private static String buildUrlParams(Class<?> clazz, Object obj) {
        StringBuilder urlParams = new StringBuilder();

        if (!(obj instanceof main.backend.base.func.util.trait.SetableFromString)) {
            return urlParams.toString();
        }

        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            String fieldValue = null;

            try {
                fieldValue = ((main.backend.base.func.util.trait.SetableFromString) obj)
                        .getFieldToString(f.getName());
            } catch (Exception e) {
                continue;
            }
            if (fieldValue == null || fieldValue.trim().isEmpty()) {
                continue;
            }
            if (urlParams.length() > 0) {
                urlParams.append("&&");
            }
            try {
                urlParams.append(f.getName()).append("=")
                        .append(java.net.URLEncoder.encode(fieldValue, "UTF-8"));
            } catch (Exception e) {
                
            }
        }
        return urlParams.toString();
    }

    private static String getRowClass(int rowIndex) {
        return (rowIndex % 2 == 0) ? "table-primary" : "table-light";
    }

    private static String createCell(Field f, Object obj, String hrefForRow, String urlParams)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        Object fObject = ReflectiveManager.getFieldObject(f, obj);
        String cellClass = getCellClass(fObject);
        String cellContent = formatCellValue(fObject);

        StringBuilder cell = new StringBuilder();
        cell.append("<td class=\"").append(cellClass).append("\">");

        if (shouldAddLink(hrefForRow, urlParams)) {
            String fullHref = buildFullHref(hrefForRow, urlParams);
            cell.append("<a href=\"").append(fullHref).append("\" class=\"text-decoration-none text-dark d-block\">")
                    .append(cellContent)
                    .append("</a>");
        } else {
            cell.append(cellContent);
        }

        cell.append("</td>");
        return cell.toString();
    }

    private static String getCellClass(Object fObject) {
        String cellClass = "align-middle";
        if (fObject instanceof Number) {
            cellClass += " text-end";
        } else if (fObject instanceof Boolean) {
            cellClass += " text-center";
        }
        return cellClass;
    }

    private static boolean shouldAddLink(String hrefForRow, String urlParams) {
        return hrefForRow != null && !hrefForRow.trim().isEmpty() && !urlParams.isEmpty();
    }

    private static String buildFullHref(String hrefForRow, String urlParams) {
        return hrefForRow + (hrefForRow.contains("?") ? "&" : "?") + urlParams;
    }

    public static String createRow(Class<?> clazz, Object obj, int rowIndex, String hrefForRow)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        String rowClass = (rowIndex % 2 == 0) ? "table-primary" : "table-light";
        sb.append("<tr class=\"").append(rowClass).append("\">");
        sb.append(createRow0(clazz, obj, rowIndex, hrefForRow));
        sb.append("</tr>");
        return sb.toString();
    }

    public static String createHeader0(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            String fieldName = f.getName() ;
            String headerClass = "align-middle bg-dark text-white";
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

    public static String createTable(Vector<Object> lObjects, String hreforRow) throws ReflectiveOperationException {
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
            sb.append(createRow(clazz, iter, i, hreforRow));
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

    private static String createEmptyTable() {
        return "<div class=\"alert alert-info text-center\">" +
                "<i class=\"bi bi-table\"></i> " +
                "Aucune donnée à afficher" +
                "</div>";
    }

    private static String formatCellValue(Object value) {
        if (value == null)
            return "<span class=\"text-muted fst-italic\">null</span>";

        return value.toString();

    }
}