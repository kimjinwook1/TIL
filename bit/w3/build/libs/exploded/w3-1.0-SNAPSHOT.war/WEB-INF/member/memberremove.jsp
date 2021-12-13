<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/todoheader.jsp" %>

<h1 class="mt-4">Member Info Modify</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <form action="/member/remove" method="post">
            <div class="mb-3">
                <label class="form-label">Id</label>
                <input type="text" class="form-control" value="${memberDTO.userid}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="password" name="userpw" class="form-control" value="${memberDTO.userpw}" readonly>
            </div>
            <div class="mb-3">
                <label class="form-label">Password</label>
                <input type="text" name="checkpw" class="form-control">
            </div>
            <div class="mb-3">
                <input type="hidden" name="uno" value="${memberDTO.uno}">
                <button type="submit" class="btn btn-primary float-end">회원 탈퇴</button>
            </div>
        </form>
    </div>
</div>
<br>

</form>

<%@include file="../includes/footer.jsp" %>