<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
<%
	String name = request.getParameter("name");
	if(name.equals("kglim")){
		out.print("<b>" + name + "</b>");
	}else if(name.equals("hong")){
		out.print("<i>" + name + "</i>");
	}else{
		out.print("<h3>" + name + "</h3>");
	}
	out.print("<hr>");

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     	<h3>EL & JSTL 바꾸어서 처리 (choose)</h3>
		<ul>
			
		</ul>
</body>
</html>