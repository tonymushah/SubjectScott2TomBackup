<%@ page import="main.map.HISTOSAL"%>
<%@ page import="main.frontend.display.FormBuilder"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <% out.println(FormBuilder.createForm(HISTOSAL.class,"../trUpdate","")); %>
</body>
</html>