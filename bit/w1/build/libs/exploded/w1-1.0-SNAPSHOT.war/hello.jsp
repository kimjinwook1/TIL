<%@ page import="java.util.Random" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%
    Random random = new Random();
    int num = random.nextInt(45) + 1;
%>
<h1><%= num%>
</h1>
<h1>Hello JSP</h1>

<br/>
</body>
</html>