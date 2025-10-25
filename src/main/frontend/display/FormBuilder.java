package main.frontend.display;

import java.lang.reflect.Field;
import java.sql.Date;

public class FormBuilder {
    public static String createLabel(Field f, Class<?> parent) {
        StringBuilder sb = new StringBuilder();
        Class<?> type = f.getType();
        sb.append("<p>");
        sb.append("<span>").append(FieldHelper.getNameHTML(f)).append(" </span>");
        sb.append("<input required ");
        if (FieldHelper.isNumericType(type)) {
            sb.append("type=\"number\"");
        } else if (Date.class.isAssignableFrom(type)) {
            sb.append("type=\"date\"");
        } else if (String.class.isAssignableFrom(type)) {
            sb.append("type=\"text\"");
        }
        sb.append(" name=\"").append(FieldHelper.getNameHTML(f)).append("\"");
        sb.append(">");
        sb.append("</p>");
        return sb.toString();

    }

    public static String createFrom0(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field f : clazz.getDeclaredFields()) {
            sb.append(createLabel(f, clazz));

        }
        return sb.toString();
    }

    public static String createFrom(Class<?> clazz) {
        StringBuilder sb = new StringBuilder("<form method=\"post\" action=\"../serialize\">");
        sb.append(createFrom0(clazz));
        sb.append("<input type=\"hidden\" name=\"class\" ").append("value=\"").append(clazz.getName()).append("\" >");
        sb.append("<input type=\"submit\" value=\"Inserer\"");
        sb.append("</from>");
        return sb.toString();
    }
}
