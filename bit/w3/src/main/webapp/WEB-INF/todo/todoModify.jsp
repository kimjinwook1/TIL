<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../includes/todoheader.jsp" %>

<h1 class="mt-4">상품 수정</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>
        <form action="/todo/modify/${dto.tno}" method="post">
            <div class="mb-3">
                <label class="form-label">TNO</label>
                <input type="text" class="form-control" name="tno" value="${dto.tno}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Title</label>
                <input type="text" class="form-control" name="title" value="${dto.title}">
                <spring:hasBindErrors name="todoDTO">
                    <c:if test="${errors.hasFieldErrors('title')}">
                        <div class="field-error">타이틀은 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <label class="form-label">Writer</label>
                <input type="text" class="form-control" name="writer" value="${dto.writer}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Date</label>
                <input type="date" class="form-control" name="dueDate" value="${dto.dueDate}">
                <spring:hasBindErrors name="todoDTO">
                    <c:if test="${errors.hasFieldErrors('dueDate')}">
                        <div class="field-error">기한은 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <label class="form-label">Finished</label>
                <input type="checkbox" name="finished" ${dto.finished?"checked":""} >
            </div>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary float-end">수정</button>
            </div>
        </form>

        <form action="/todo/remove" method="post">
            <input type="hidden" name="tno" value="${dto.tno}">
            <button type="submit" class="btn btn-danger">삭제</button>
        </form>

    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>
