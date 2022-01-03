<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/todoheader.jsp" %>

<h1 class="mt-4">Todo</h1>
<c:if test="${param.status}">
    <h2>등록 완료</h2>
</c:if>
<c:if test="${param.modifyStatus}">
    <h2>수정 완료</h2>
</c:if>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <div class="mb-3">
            <label class="form-label">TNO</label>
            <input type="text" class="form-control" value="${dto.tno}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" class="form-control" value="${dto.title}" readonly>

        </div>
        <div class="mb-3">
            <label class="form-label">Writer</label>
            <input type="text" class="form-control" value="${dto.writer}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">Date</label>
            <input type="date" class="form-control" value="${dto.dueDate}" disabled>

        </div>
        <div class="mb-3">
            <label class="form-label">Finished</label>
            <input type="checkbox" ${dto.finished?"checked":""} disabled>
        </div>
        <div class="mb-3">
            <div class="mb-3">
                <div class="col">
                    <button type="submit" onclick="location.href='/todo/modify/${dto.tno}'"
                            class="btn btn-primary float-end">수정/삭제
                    </button>
                </div>
                <a href="/todo/list" class="btn btn-primary float-lg-start">목록</a>
            </div>

        </div>
    </div>
    <br>

<%@include file="../includes/footer.jsp" %>