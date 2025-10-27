package main.frontend.display.helper;

import java.sql.SQLException;
import java.util.Vector;

import main.base.Err.NoDataToUpdateErr;
import main.frontend.display.BodyBuilder;
import main.frontend.display.FormBuilder;
import main.frontend.display.TableBuilder;
import main.map.EMP;
import main.map.HISTOSAL;
import main.map.V_SALAIRE_EMP_PROCHE;

public class PageHelper {
    public static String getContentForInfoPage() throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        Vector<Object> lo = (new EMP()).find();
        return BodyBuilder.makeDefaultBody(TableBuilder.createTable(lo));
    }

    public static String getContentForPageOne() {
        return BodyBuilder.makeDefaultBody(FormBuilder.createForm(HISTOSAL.class, "../trUpdate", ""));
    }

    public static String getContentForPageTwo() {
        String otherInputHtml = "<p>DATE DE FILTRE <input required type=\"date\" name=\"DATE-REQUIRED\"></p>"
                + "\n<h1>Autre condition </h1>";
        String form = FormBuilder.createForm(V_SALAIRE_EMP_PROCHE.class, "../displaySubject2", otherInputHtml);
        return BodyBuilder.makeDefaultBody(form);
    }
}
