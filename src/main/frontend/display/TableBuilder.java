package main.frontend.display;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import main.base.func.util.ReflectiveManager;

public class TableBuilder {
     public static String createRow0(Class<?> clazz, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder sb = new StringBuilder();
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
            Object fObject = ReflectiveManager.getFieldObject(f, obj);
               sb.append("<td>").append("" + fObject).append("</td>");
            }
        return sb.toString();
        }
     public static String createRow(Class<?> clazz, Object obj) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        StringBuilder sb=new StringBuilder();
        sb.append("<tr>");
        sb.append(createRow0(clazz, obj));
        sb.append("</tr>");
        return sb.toString();
    }

    public static String createHeader0(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        for (Field f : ReflectiveManager.getFieldRecursives(clazz)) {
                sb.append("<th>").append(FieldHelper.getNameHTML(f)).append("</th>");
            
        }
        return sb.toString();
    }

    public static String createHeader(Class<?> clazz) {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr>").append(createHeader0(clazz)).append("</tr>");
        return sb.toString();
    }
    public static String createTable(Vector<Object> lObjects) throws ReflectiveOperationException{
        StringBuilder sb = new StringBuilder("<table border=\"2\">");
        
        Class<?> clazz=lObjects.get(0).getClass();
        sb.append(createHeader(clazz));
        for (Object iter : lObjects) {
            sb.append(createRow(clazz, iter));   
        }
        sb.append("</table>");
        return sb.toString();
    }    

        
    }

   
