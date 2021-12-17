<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/todoheader.jsp" %>

<h1 class="mt-4">Modify Sidebar</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>

        <form method="post">

        <div class="mb-3">
            <label class="form-label">TNO</label>
            <input type="text" class="form-control" name="tno" value="${dto.tno}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">Title</label>
            <input type="text" class="form-control" name="title" value="${dto.title}">
        </div>
        <div class="mb-3">
            <label class="form-label">Writer</label>
            <input type="text" class="form-control" value="${dto.writer}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">Date</label>
            <input type="date" class="form-control" name="dueDate" value="${dto.dueDate}">
        </div>
        <div class="mb-3">
            <label class="form-label">Finished</label>
            <input type="checkbox" name="checkbox" ${dto.finished?"checked":""} >
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