<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    Integer count = (Integer) session.getAttribute("count");
    if (count == null) {
        count = 1;
    } else {
        count++;
    }
    session.setAttribute("count", count);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>カウンター</title>
</head>
<body>
    <p>count: ${sessionScope.count}</p> <!-- EL式を使用 -->

    <form action="elPractice02.jsp" method="post">
        <button type="submit">カウントアップ</button>
    </form>
</body>
</html>
