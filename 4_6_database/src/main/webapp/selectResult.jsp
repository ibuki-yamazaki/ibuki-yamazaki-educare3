<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jp.co.web.UserInfoDto" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果画面</title>
</head>
<body>
<h1>検索結果</h1>

<!-- 検索条件の表示 -->
<h2>検索条件</h2>
<table border="1">
<tr>
<th>項目</th>
<th>検索値</th>
</tr>
<tr>
<td>名前</td>
<td><%= request.getAttribute("searchUserName") != null ? request.getAttribute("searchUserName") : "" %></td>
</tr>
<tr>
<td>TEL</td>
<td><%= request.getAttribute("searchTelephone") != null ? request.getAttribute("searchTelephone") : "" %></td>
</tr>
</table>

<!-- エラーメッセージの表示 -->
<% if (request.getAttribute("noResultMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("noResultMessage") %></p>
<% } %>

<!-- 検索結果の表示 -->
<%
List<UserInfoDto> userList = (List<UserInfoDto>) request.getAttribute("userList");
if (userList != null && !userList.isEmpty()) {
%>
<h2>検索結果一覧</h2>
<table border="1">
<tr>
<th>ユーザーID</th>
<th>ログインID</th>
<th>名前</th>
<th>電話番号</th>
<th>権限</th>
</tr>
<% for (UserInfoDto user : userList) { %>
<tr>
<td><%= user.getUserId() %></td>
<td><%= user.getLoginId() %></td>
<td><%= user.getUserName() %></td>
<td><%= user.getTelephone() %></td>
<td><%= user.getRoleId() %></td>
</tr>
<% } %>
</table>
<% } %>

<p>
<a href="SelectServlet">検索画面に戻る</a> | 
<a href="menu.jsp">メニューに戻る</a>
</p>
</body>
</html>