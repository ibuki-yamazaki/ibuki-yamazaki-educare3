<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
</head>
<body>
<h1>検索画面</h1>

<p>検索したいデータ情報を入力してください</p>
<p>適合する全ての要員を全検索を行います</p>

<form action="SelectServlet" method="post">
<table>
<tr>
<td>名前</td>
<td><input type="text" name="user_name"></td>
</tr>
<tr>
<td>TEL</td>
<td><input type="text" name="telephone"></td>
</tr>
<tr>
<td colspan="2">
<input type="submit" value="検索">
</td>
</tr>
</table>
</form>

<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>

<p><a href="menu.jsp">メニューに戻る</a></p>
</body>
</html>