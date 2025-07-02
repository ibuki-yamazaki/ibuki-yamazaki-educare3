<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductDao" %>
<%@ page import="model.Product" %>

<html>
<head><title>商品検索</title></head>
<body>
<h3>検索条件を入力してください</h3>
<form action="DBConnection_JavaEE01" method="post">
    id: <input type="text" name="id"><br>
    name: <input type="text" name="name"><br>
    price: <input type="text" name="price"><br>
    <input type="submit" value="検索">
</form>

<hr>

<table border="1">
<tr><th>id</th><th>name</th><th>price</th></tr>
<%
    List<Product> list = (List<Product>) request.getAttribute("products");
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