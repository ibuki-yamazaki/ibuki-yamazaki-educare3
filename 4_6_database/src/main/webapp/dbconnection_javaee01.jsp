<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductDAO" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<html>
<head><title>商品検索</title></head>
<body>
<form action="DBConnection_JavaEE01" method="post">
    <div>検索条件を入力してください</div>
    id: <input type="text" name="id"><br>
    name: <input type="text" name="name"><br>
    price: <input type="text" name="price"><br>
    <input type="submit" value="検索">
</form>

<%
    // 検索結果があればテーブル表示
    java.util.List<Product> list = (java.util.List<Product>)request.getAttribute("result");
    if (list != null) {
%>
    <table border="1">
        <tr><th>id</th><th>name</th><th>price</th></tr>
        <% for(Product p : list) { %>
            <tr>
                <td><%= String.valueOf(p.getId()) %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
            </tr>
        <% } %>
    </table>
<% } %>
</body>
</html>