<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		String id = request.getParameter("userid");
		
		request.setAttribute("name", "korea");
		session.setAttribute("user", "bit");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--  
EL(출력표현식) : JSP 페이지에서 사용되는 스크립트 언어(화면에 출력)
이유 : 스파게티 코드(% 영역과 UI(html, css) 혼재) > 유지보수 어려움 , 코드의 해석(가독성)

문제 해결 : html 와도 잘 어울리고 서버쪽 출력 언어 script >> EL & JSTL >> 단점(JSP 페이지에서만 사용)

EL : tomcat 이 내장하고 있는 자원 (별도의 참조 없이 사용가능)

EL 가지고 있는 객체
1. param
2. paramValues
3. requsetScope
4. sessionScope
5. applicationScope

-->
	<h3>JSP</h3>
	<b><%= id %></b><br>
	<b><%= request.getAttribute("name") %></b><br>
	<b><%= session.getAttribute("user") %></b><br>
	<b><%= request.getParameter("userid") %></b><br>
	<hr>
	<h3>EL 출력표현식</h3>
	기존 <%= 1+5 %><br>
	EL : ${100+50}<br>
	EL : ${"1" + 1}<br><!-- 문자형 숫자 (자동 형변환) 숫자 + 숫자  -->
	EL : ${1==1}<br>
	EL : ${false}<br>
	EL : ${!false}<br>
	EL : ${empty x}<br><!--  x라는 변수의 값이 비어있니 -->
</body>
</html>




