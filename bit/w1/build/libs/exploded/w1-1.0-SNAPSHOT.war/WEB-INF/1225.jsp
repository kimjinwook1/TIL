<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>${list}</h2>

<h1>
    크리스마스에는 축복을..단 커플에게는 저주를...
</h1>
<h1><%= Thread.currentThread().getName()%>
</h1>

<ul>
    <c:forEach var="sampleDTO" items="${list}">
        <li>${sampleDTO.first} --- ${sampleDTO.last}</li>
        <%--<li>${sampleDTO} ${sampleDTO.first == null?"aaa":"bbb"}</li>--%>
    </c:forEach>
</ul>

<c:if test="${1 + 2 == 3}">a </c:if> <%--if test = 이 트루일경우 a가 출력됨--%>

</body>
</html>
