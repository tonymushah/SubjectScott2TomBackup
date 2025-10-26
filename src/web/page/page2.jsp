<%@ page import="main.map.V_SALAIRE_EMP_PROCHE"%>
<%@ page import="main.frontend.display.FormBuilder"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% 
    String otherInputHtml="<p>DATE DE FILTRE <input required type=\"date\" name=\"DATE-REQUIRED\"></p>"+"\n<h1>Condition Supplémentaire</h1>";
    out.println(FormBuilder.createForm(V_SALAIRE_EMP_PROCHE.class,"../displaySubject2",otherInputHtml)); %>
</body>
</html>