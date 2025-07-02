<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductDao" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<html>
<head><title>商品管理</title></head>
<body>
<form action="DBConnection_JavaEE03" method="post">
    <div>検索条件、または、登録情報（name,price）を入力してください</div>
    id: <input type="text" name="id"><br>
    name: <input type="text" name="name"><br>
    price: <input type="text" name="price"><br>
    <input type="submit" name="action" value="検索">
    <input type="submit" name="action" value="登録">
    <input type="submit" name="action" value="更新">
</form>
<br>
<table border="1">
    <tr><th>id</th><th>name</th><th>price</th></tr>
    <%
    List<Product> list = (List<Product>)request.getAttribute("result");
    if (list != null) {
        for (Product p : list) {
    %>
    <tr>
        <td><%= String.valueOf(p.getId()) %></td>
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
