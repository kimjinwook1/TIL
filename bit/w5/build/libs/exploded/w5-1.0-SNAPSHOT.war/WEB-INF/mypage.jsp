<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>당신은 로그인을 하였습니다.</h1>
<h1> ${userInfo}</h1>
<h1> userid: ${userInfo.userid}</h1>
<h1> userpw: ${userInfo.userpw}</h1>
<h1> username: ${userInfo.username}</h1>

<form action="/logout" method="post">
    <button type="submit">logout</button>
</form>
</body>
</html>
