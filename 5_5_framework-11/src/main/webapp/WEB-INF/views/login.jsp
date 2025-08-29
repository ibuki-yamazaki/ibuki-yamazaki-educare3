<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/login">
    ID: <input type="text" name="id"><br>
    PASS: <input type="password" name="pass"><br>
    <input type="submit" value="ログイン">
</form>

<c:if test="${not empty errorMessage}">
    <div style="color:red">${errorMessage}</div>
</c:if>

<a href="/">TOP画面に戻る</a>
</body>
</html>