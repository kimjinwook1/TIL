<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp" %>
<h1 class="mt-4">Sign UP</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>

        <form action="/member/signup" method="post">
            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" name="userid" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">PASSWORD</label>
                <input type="password" name="userpw" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">PASSWORD</label>
                <input type="password" name="checkpw" class="form-control">
            </div>
            <div class="mb-3">
                <label class="form-label">USERNAME</label>
                <input type="text" name="username" class="form-control">
            </div>
            <div class="mb-3">
                <button type="submit" class="btn btn-primary float-end">SIGN UP</button>
            </div>
        </form>
    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>