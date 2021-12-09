<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp" %>

<h1 class="mt-4">Register Sidebar</h1>
<form action="/todo/register" method="post">

    <input type="text" name="title">
    <input type="text" name="writer">
    <input type="date" name="dueDate">
    <button type="submit">제출</button>

</form>

<%@include file="../includes/footer.jsp" %>