package main.frontend.display.helper;

import java.sql.SQLException;
import java.util.Vector;

import main.backend.base.Err.NoDataToUpdateErr;
import main.common.map.EMP;
import main.common.map.HISTOSAL;
import main.common.map.V_SALAIRE_DEPT_PROCHE;
import main.frontend.display.BodyBuilder;
import main.frontend.display.FormBuilder;
import main.frontend.display.TableBuilder;
public class PageHelper {
    public static String getContentForInfoPage() throws SQLException, ReflectiveOperationException, NoDataToUpdateErr {
        Vector<Object> lo = (new EMP()).find();
        String content=TableBuilder.createTable(lo,"#");
        return BodyBuilder.makeDefaultBody(content);
    }

    public static String getContentForInsertPage() throws SQLException, ReflectiveOperationException, NoDataToUpdateErr{
        return BodyBuilder.makeDefaultBody(FormBuilder.createForm(EMP.class,"../InsertEmp", "",false));
    }

    public static String getContentForPageOne() {
        Class<?>[] classes={HISTOSAL.class,EMP.class};
        boolean[] skipmodes={false,true};
        return BodyBuilder.makeDefaultBody(FormBuilder.createForm(classes, "../trUpdate", "", skipmodes));
    }

    public static String getContentForPageTwo() {
        String otherInputHtml = "<p>DATE DE FILTRE <input required type=\"date\" name=\"DATE-REQUIRED\"></p>";
        String form = FormBuilder.createForm(EMP.class, "../displaySubject2", otherInputHtml,false);
        return BodyBuilder.makeDefaultBody(form);
    }

    public static String getContentForPage_dept() {
        String otherInputHtml = "<p>DATE DE FILTRE <input required type=\"date\" name=\"DATE-REQUIRED\"></p>";
        String form = FormBuilder.createForm(V_SALAIRE_DEPT_PROCHE.class, "../displayByDept", otherInputHtml,false);
        return BodyBuilder.makeDefaultBody(form);
    }
}
