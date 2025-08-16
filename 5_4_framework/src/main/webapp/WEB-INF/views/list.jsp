<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="page.title.list" /></title>
   
</head>
<body>
    <div class="container">
        <h1><spring:message code="page.title.list" /></h1>
        
        <div class="result-section">
            <h2><spring:message code="title.search.result" /></h2>
            
            <c:if test="${empty products}">
                <div class="no-results">
                    <p><spring:message code="message.no.products" /></p>
                </div>
            </c:if>
            
            <c:if test="${not empty products}">
                <table>
                    <thead>
                        <tr>
                            <th>id</th>
                            <th><spring:message code="label.name" /></th>
                            <th><spring:message code="label.price" /></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            
            <div>
                <a href="/" class="back-btn"><spring:message code="button.back" /></a>
            </div>
        </div>
    </div>
</body>
</html>