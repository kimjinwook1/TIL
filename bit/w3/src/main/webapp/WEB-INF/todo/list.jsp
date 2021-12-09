<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@include file="../includes/header.jsp" %>
<h1 class="mt-4">Todo List</h1>

<table class="table">

    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Title</th>
        <th scope="col">DueDate</th>
        <th scope="col">Writer</th>
        <th scope="col">Complete</th>

    </tr>
    </thead>
    <tbody>

    <c:forEach items="${dtoList}" var="dto">
        <tr>
            <td>${dto.tno}</td>
            <td><a href="/todo/read/${dto.tno}">${dto.title}</a></td>
            <td>${dto.dueDate}</td>
            <td>${dto.writer}</td>
            <td>${dto.finished?"Done":"Not Yet"}</td>
        </tr>
    </c:forEach>

    </tbody>
</table>

<%@include file="../includes/footer.jsp" %>

