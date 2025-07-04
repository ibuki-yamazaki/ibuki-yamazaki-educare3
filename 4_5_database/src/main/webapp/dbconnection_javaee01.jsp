<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品検索</title>
</head>
<body>
    <h2>商品検索フォーム</h2>

    <form action="DBConnection_JavaEE01" method="post">
        <fieldset>
            <legend>検索条件を入力してください</legend>
            id: <input type="text" name="id" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>"><br>
            name: <input type="text" name="name" value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>"><br>
            price: <input type="text" name="price" value="<%= request.getParameter("price") != null ? request.getParameter("price") : "" %>"><br>
            <input type="submit" value="検索">
        </fieldset>
    </form>

    <br>

   
</body>
</html>
