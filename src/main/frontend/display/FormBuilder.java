package main.frontend.display;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.base.annotation.IdFieldDropDown;
import main.base.func.special.SpecialQueryBuilder;
import main.base.func.util.ReflectiveManager;

public class FormBuilder {
    public static String getdropdown(Field f) {
        StringBuilder sb = new StringBuilder();

        try {
            HashMap<String, String> options = SpecialQueryBuilder.getNameDisplay(f);
            sb.append("<select");
            sb.append(" id=\"").append(f.getName()).append("\"");
            sb.append(" name=\"").append(f.getName()).append("\"");
            sb.append(" class=\"form-control select-dropdown\">\n");
            sb.append("  <option value=\"\">-- Sélectionner --</option>\n");
            for (Map.Entry<String, String> entry : options.entrySet()) {
                sb.append("  <option value=\"")
                        .append(entry.getKey())
                        .append("\">")
                        .append(entry.getKey()+ " : "+entry.getValue())
                        .append("</option>\n");
            }

            sb.append("</select>");

        } catch (SQLException e) {
            sb.append("<div class=\"error-message\">Erreur de chargement des données</div>");
        }

        return sb.toString();
    }

    public static String createLabel(Field f, Class<?> parent) {
        StringBuilder sb = new StringBuilder();
        Class<?> type = f.getType();

        sb.append("<div class=\"form-group\">");
        sb.append("<label class=\"form-label field-label\">").append(FieldHelper.getNameHTML(f)).append("</label>");
        if(f.isAnnotationPresent(IdFieldDropDown.class)){
            sb.append(getdropdown(f));
        }else{
               sb.append("<input class=\"form-control input-field\" ");

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
        }
     
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
        StringBuilder sb = new StringBuilder("<form method=\"post\" class=\"custom-form container mt-4\" ")
                .append(" action=\"").append(action).append("\">\n");

        sb.append("<div class=\"form-card card\">");
        sb.append("<div class=\"card-body form-card-body\">");

        sb.append(OtherInputsHtml).append("\n");
        sb.append(createFrom0(clazz));

        sb.append("<input type=\"hidden\" name=\"class\" ")
                .append("value=\"").append(clazz.getName()).append("\" >\n");

        sb.append("<div class=\"form-actions d-grid\"><br>");
        sb.append("<input type=\"submit\" value=\"Valider\" class=\"btn btn-primary btn-lg w-100\">\n");
        sb.append("</div>");

        sb.append("</div>");
        sb.append("</div>");
        sb.append("</form>\n");
        return sb.toString();
    }
}