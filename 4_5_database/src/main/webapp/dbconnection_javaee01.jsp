<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="model.ProductDao" %>
<%@ page import="model.Product" %>

<html>
<head>
 <meta charset="UTF-8">
    <title>商品検索</title>
</head>
<body>
    <form action="DBConnection_JavaEE01" method="post">
        <fieldset>
            <legend>検索条件を入力してください</legend>
            id: <input type="text" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>"><br>
            name: <input type="text" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"><br>
            price: <input type="text" name="price" value="<%= request.getParameter("price") != null ? request.getParameter("price") : "" %>"><br>
            <input type="submit" value="検索">
        </fieldset>
    </form>

    <%
    List<Product> list = (List<Product>)request.getAttribute("productList");
            if (list != null && !list.isEmpty()) {
    %>
    <table border="1">
        <tr>
            <th>id</th><th>name</th><th>price</th>
        </tr>
        <%
            for (Product p : list) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %></td>
        </tr>
        <% } %>
    </table>
    <% } else if (list != null) { %>
        <p>該当する商品はありません。</p>
    <% } %>
</body>
</html>