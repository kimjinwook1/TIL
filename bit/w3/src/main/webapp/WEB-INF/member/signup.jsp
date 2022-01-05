<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../includes/header.jsp" %>
<h1 class="mt-4">Sign UP</h1>
<div class="card" style="width: 70vw;">
    <div class="card-body">
        <h5 class="card-title">Card title</h5>

        <form action="/member/signup" method="post">
            <div class="mb-3">
                <label class="form-label">ID</label>
                <input type="text" name="userId" class="form-control">
                <spring:hasBindErrors name="memberDTO">
                    <c:if test="${errors.hasFieldErrors('userId')}">
                        <div class="field-error">아이디는 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <label class="form-label">PASSWORD</label>
                <input type="password" name="userPw" class="form-control">
                <spring:hasBindErrors name="memberDTO">
                    <c:if test="${errors.hasFieldErrors('userPw')}">
                        <div class="field-error">비밀번호는 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <label class="form-label">PASSWORD</label>
                <input type="password" name="checkPw" class="form-control">
                <spring:hasBindErrors name="memberDTO">
                    <c:if test="${errors.hasFieldErrors('checkPw')}">
                        <div class="field-error">비밀번호는 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
            </div>
            <div class="mb-3">
                <label class="form-label">USERNAME</label>
                <input type="text" name="username" class="form-control">
                <spring:hasBindErrors name="memberDTO">
                    <c:if test="${errors.hasFieldErrors('username')}">
                        <div class="field-error">이름은 필수 값 입니다.</div>
                    </c:if>
                </spring:hasBindErrors>
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