<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="jp.co.sample.pet.Animal" %>
<%@ page import="jp.co.sample.pet.Dog" %>
<%@ page import="jp.co.sample.pet.Cat" %>

<%
    Animal pet = (Animal) request.getAttribute("selectedPet");
    String type = (String) request.getAttribute("type");
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
    <h2>ペット情報</h2>

    <table class="petInfoTable">
      <tr>
        <th>名前：</th>
        <td><%= pet.getName() %></td>
      </tr>
      <tr>
        <th>年齢：</th>
        <td><%= pet.getAge() %></td>
      </tr>
      <tr>
        <th>体長：</th>
        <td><%= pet.getHeight() %></td>
      </tr>
      <tr>
        <th>体重：</th>
        <td>
          <%= pet.getWeight() %>kg
          <% if (pet.isFat()) { %>
            <span class="error">※太り気味です</span>
          <% } %>
        </td>
      </tr>
      <tr>
        <th>種別：</th>
        <td><%= pet.getType() %></td>
      </tr>

      <% if ("dog".equals(type)) { %>
        <tr><th>散歩場所：</th><td><%= ((Dog) pet).getWalkingPlace() %></td></tr>
      <% } else if ("cat".equals(type)) { %>
        <tr><th>お気に入り遊具：</th><td><%= ((Cat) pet).getFavoriteItem() %></td></tr>
      <% } %>
    </table>
  </div>

  <form action="petList.jsp">
    <button class="btn" type="submit">戻る</button>
  </form>

  <form action="index.jsp">
    <button class="btn" type="submit">先頭に戻る</button>
  </form>
</body>
</html>
