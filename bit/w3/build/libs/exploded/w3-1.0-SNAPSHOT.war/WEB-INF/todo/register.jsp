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
            </div>
            <div class="mb-3">
                <label class="form-label">Writer</label>
                <input type="text" name="writer" class="form-control" placeholder="Writer..">
            </div>
            <div class="mb-3">
                <label class="form-label">Date</label>
                <input type="date" name="dueDate" class="form-control">
            </div>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary float-end">Register</button>
            </div>

<%--            <br>--%>
<%--            <br>--%>
<%--            <br>--%>
<%--            <div class="d-grid gap-2">--%>
<%--                <button class="btn btn-primary" type="button">등록</button>--%>
<%--            </div>--%>
        </form>
    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>