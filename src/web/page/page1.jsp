<%@ page import="main.map.HISTOSAL"%>
<%@ page import="main.frontend.display.FormBuilder"%>
<%@ page import="main.frontend.display.BodyBuilder"%>
    <% 
    out.println(BodyBuilder.makeDefaultBody(FormBuilder.createForm(HISTOSAL.class,"../trUpdate",""))); %>
