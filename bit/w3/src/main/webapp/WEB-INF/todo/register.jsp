<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/todoheader.jsp" %>

<h1 class="mt-4">Register Sidebar</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>

        <form action="/todo/register" method="post">

            <div class="mb-3">
                <label class="form-label">Title</label>
                <input type="text" name="title" class="form-control" placeholder="Title..">
                <spring:hasBindErrors name="todoDTO">
                    <c:if test="${errors.hasFieldErrors('title')}">
                        <div class="field-error">타이틀은 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <label class="form-label">Date</label>
                <input type="date" name="dueDate" class="form-control">
                <spring:hasBindErrors name="todoDTO">
                    <c:if test="${errors.hasFieldErrors('dueDate')}">
                        <div class="field-error">기한은 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary float-end">Register</button>
            </div>
            <input type="hidden" name="writerid" value="${userInfo.uno}">
            <input type="hidden" name="writer" value="${userInfo.username}">
        </form>
    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>