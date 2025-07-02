<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.Product" %>
<%@ page import="model.ProductDao" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>検索条件、または、登録情報（name,price）を入力してください</h3>
<form action="DBConnection_JavaEE05" method="post">
    id: <input type="text" name="id"><br>
    name: <input type="text" name="name"><br>
    price: <input type="text" name="price"><br>
    <input type="submit" name="action" value="search">
    <input type="submit" name="action" value="registerOrUpdate">
    <input type="submit" name="action" value="delete">
</form>

<table border="1">
<tr><th>id</th><th>name</th><th>price</th></tr>
<%
    List<Product> list = (List<Product>)request.getAttribute("products");
    if (list != null) {
        for (Product p : list) {
%>
<tr>
    <td><%= p.getId() %></td>
    <td><%= p.getName() %></td>
    <td><%= p.getPrice() %></td>
</tr>
<%
        }
    }
%>
</table>
</body>
</html>