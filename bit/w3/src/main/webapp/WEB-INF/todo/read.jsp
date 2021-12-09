<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp" %>

<h1 class="mt-4">Register Sidebar</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>


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
            <a href="/todo/modify/${dto.tno}" class="btn btn-primary float-end">수정/삭제</a>
            <a href="/todo/list" class="btn btn-info float-end">목록</a>
        </div>

    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>