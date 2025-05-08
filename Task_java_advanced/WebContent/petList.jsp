<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="jp.co.sample.servlet.PetSessionInfo" %>
<%@ page import="jp.co.sample.servlet.PetSessionInfo" %>

<%
    PetSessionInfo petInfo = (PetSessionInfo) session.getAttribute("petInfo");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java応用課題</title>
<link href="css/styles.css" rel="stylesheet">
</head>
<body>
  <h1>ペット情報管理</h1>

  <div class="info">
    <h2>ペット一覧</h2>
    <form action="PetListServlet" method="get">
      <button class="btn" type="submit" name="btn" value="dog"><%= petInfo.getDogName() %></button>
      <button class="btn" type="submit" name="btn" value="cat"><%= petInfo.getCatName() %></button>
    </form>
  </div>

  <form action="PetListServlet" method="get">
    <button class="btn" type="submit">先頭に戻る</button>
  </form>
</body>
</html>