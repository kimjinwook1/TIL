<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp" %>

<h1 class="mt-4">Login</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>

        <form action="/login" method="post">

            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" name="userid" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">PASSWORD</label>
                <input type="text" name="userpw" class="form-control">
            </div>
            <label>Remember-me</label>
            <input type="checkbox" name="rememberme">
            <div class="mb-3">
                <button type="submit" class="btn btn-primary float-end">LOGIN</button>
            </div>

        </form>
    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>