package main.frontend.display;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import main.backend.base.annotation.IdDropDown;
import main.backend.base.annotation.SkipFormulaire;
import main.backend.base.func.special.SpecialQueryBuilder;
import main.backend.base.func.util.ReflectiveManager;

public class FormBuilder {
    public static String getdropdown(Field f) {
        StringBuilder sb = new StringBuilder();

        try {
            HashMap<String, String> options = SpecialQueryBuilder.getNameSelectable(f);
            sb.append("<select");
            sb.append(" id=\"").append(FieldHelper.getNameHTML(f)).append("\"");
            sb.append(" name=\"").append(FieldHelper.getNameHTML(f)).append("\"");
            sb.append(" class=\"form-select form-select-lg shadow-sm border-0 rounded-3\">\n");
            sb.append("  <option value=\"\"><i class=\"bi bi-list-ul me-2\"></i>-- Sélectionner --</option>\n");
            for (Map.Entry<String, String> entry : options.entrySet()) {
                sb.append("  <option value=\"")
                        .append(entry.getKey())
                        .append("\"><i class=\"bi bi-circle-fill me-2 small text-primary\"></i>")
                        .append(entry.getKey() + " : " + entry.getValue())
                        .append("</option>\n");
            }

            sb.append("</select>");

        } catch (SQLException e) {
            sb.append("<div class=\"alert alert-danger d-flex align-items-center rounded-3 border-0 shadow-sm\">");
            sb.append("<i class=\"bi bi-exclamation-triangle me-2\"></i>");
            sb.append("Erreur de chargement des données");
            sb.append("</div>");
        }

        return sb.toString();
    }

    public static void handleValidFieldNotDropDown(StringBuilder sb, Class<?> type, Field f) {
        sb.append("<input class=\"form-control form-control-lg shadow-sm border-0 rounded-3 px-3\" ");

        if (FieldHelper.isNumericType(type)) {
            sb.append("type=\"number\" placeholder=\" Entrez un nombre\"");
        } else if (Date.class.isAssignableFrom(type)) {
            sb.append("type=\"date\"");
        } else if (String.class.isAssignableFrom(type)) {
            sb.append("type=\"text\" placeholder=\" Entrez du texte\"");
        }

        sb.append(" name=\"").append(FieldHelper.getNameHTML(f)).append("\"");
        sb.append(">");
    }

    public static String createLabel(Field f, Class<?> parent) {
        StringBuilder sb = new StringBuilder();
        Class<?> type = f.getType();

        sb.append("<div class=\"mb-4\">");
        sb.append("<label class=\"form-label fw-bold text-gradient mb-3 d-flex align-items-center\">")
                .append("<i class=\"bi bi-tag-fill me-2\"></i>" + FieldHelper.getNameHTML(f)).append("</label>");

        if (f.isAnnotationPresent(IdDropDown.class)) {
            sb.append(getdropdown(f));
        } else {
            handleValidFieldNotDropDown(sb, type, f);
        }
        sb.append("</div>");

        return sb.toString();
    }

    public static String createFrom0(Class<?> clazz, boolean skipmode) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div class=\"row g-4\">");
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            if (skipmode && f.isAnnotationPresent(SkipFormulaire.class))
                continue;
            sb.append("<div class=\"col-12\">");
            sb.append(createLabel(f, clazz));
            sb.append("</div>");
        }
        sb.append("</div>");
        return sb.toString();
    }

    public static void addOptionalInputHtml(StringBuilder sb, String OtherInputsHtml) {
        if (OtherInputsHtml != null && !OtherInputsHtml.trim().isEmpty()) {
            sb.append("<div class=\"alert alert-info border-0 rounded-3 shadow-sm mb-4\">");
            sb.append("<div class=\"d-flex align-items-center\">");
            sb.append("<i class=\"bi bi-info-circle-fill me-2\"></i>");
            sb.append("<span class=\"fw-medium\">Input : </span>");
            sb.append("</div>");
            sb.append("<div class=\"mt-2\">");
            sb.append(OtherInputsHtml);
            sb.append("</div>");
            sb.append("</div>");
        }
    }

    public static StringBuilder getPreparedStringBuilderForm(String action, String OtherInputsHtml) {
        StringBuilder sb = new StringBuilder("<form method=\"post\" class=\"container mt-4\" ")
                .append(" action=\"").append(action).append("\">\n");

        sb.append("<div class=\"card border-0 shadow-lg rounded-4 overflow-hidden\">");
        sb.append("<div class=\"card-header gradient-bg text-white py-4\">");
        sb.append("<div class=\"d-flex align-items-center\">");
        sb.append("<i class=\"bi bi-plus-circle-fill me-3 fs-2\"></i>");
        sb.append("<div>");
        sb.append("<h3 class=\"card-title mb-0 fw-bold\">Formulaire :</h3>");
        sb.append("<p class=\"mb-0 opacity-75\"><i class=\"bi bi-database me-1\"></i>Système de gestion Oracle</p>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");

        sb.append("<div class=\"card-body p-5 bg-light\">");
        addOptionalInputHtml(sb, OtherInputsHtml);

        return sb;
    }

    public static void fillStringBuilderForm(StringBuilder sb) {
        sb.append("<div class=\"d-grid gap-3 mt-5\">");
        sb.append(
                "<button type=\"submit\" class=\"btn btn-success btn-lg py-3 fw-bold rounded-3 border-0 shadow-sm\">");
        sb.append("<i class=\"bi bi-check-circle-fill me-2\"></i>");
        sb.append("Valider");
        sb.append("</button>");
        sb.append("</div>");

        sb.append("</div>");
        sb.append("</div>");
        sb.append("</form>\n");
    }

    public static String createForm(Class<?> clazz, String action, String OtherInputsHtml, boolean skipmode) {
        StringBuilder sb = getPreparedStringBuilderForm(action, OtherInputsHtml);
        sb.append(createFrom0(clazz, skipmode));

        sb.append("<input type=\"hidden\" name=\"class\" ")
                .append("value=\"").append(clazz.getName()).append("\" >\n");

        fillStringBuilderForm(sb);
        return sb.toString();
    }

    public static String createForm(Class<?>[] clazz, String action, String OtherInputsHtml, boolean[] skipmodes) {
        StringBuilder sb = getPreparedStringBuilderForm(action, OtherInputsHtml);

        for (int i = 0; i < clazz.length; i++) {
            sb.append("<h1>Formulaire pour : ").append(clazz[i].getSimpleName()).append("</h1>");
            sb.append(createFrom0(clazz[i], skipmodes[i]));
        }
        fillStringBuilderForm(sb);
        return sb.toString();
    }
}