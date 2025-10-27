package main.frontend.display;

import java.lang.reflect.Field;
import java.sql.Date;

import main.base.func.util.ReflectiveManager;

public class FormBuilder {
    public static String createLabel(Field f, Class<?> parent) {
        StringBuilder sb = new StringBuilder();
        Class<?> type = f.getType();
        
        sb.append("<div class=\"mb-3\">");
        sb.append("<label class=\"form-label\">").append(FieldHelper.getNameHTML(f)).append("</label>");
        sb.append("<input class=\"form-control\" ");
        
        if (FieldHelper.isNumericType(type)) {
            sb.append("type=\"number\"");
        } else if (Date.class.isAssignableFrom(type)) {
            sb.append("type=\"date\"");
        } else if (String.class.isAssignableFrom(type)) {
            sb.append("type=\"text\"");
        }
        
        sb.append(" name=\"").append(FieldHelper.getNameHTML(f)).append("\"");
        sb.append(">");
        sb.append("</div>");
        return sb.toString();
    }

    public static String createFrom0(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            sb.append(createLabel(f, clazz));
        }
        return sb.toString();
    }

    public static String createForm(Class<?> clazz, String action, String OtherInputsHtml) {
        StringBuilder sb = new StringBuilder("<form method=\"post\" class=\"container mt-4\" ")
                .append(" action=\"").append(action).append("\">\n");
        
        sb.append("<div class=\"card\">");
        sb.append("<div class=\"card-body\">");
        
        sb.append(OtherInputsHtml).append("\n");
        sb.append(createFrom0(clazz));
        
        sb.append("<input type=\"hidden\" name=\"class\" ")
          .append("value=\"").append(clazz.getName()).append("\" >\n");
        
        sb.append("<div class=\"d-grid\">");
        sb.append("<input type=\"submit\" value=\"Valider\" class=\"btn btn-primary\">\n");
        sb.append("</div>");
        
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</form>\n"); 
        return sb.toString();
    }
}