<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Validation</title>
</head>
<body>

<form:form action="test" modelAttribute="test">
  <div>名前：<form:input path="name" /><form:errors path="name" cssStyle="color: red"/></div>
  <div>ポイント：<form:input path="point" /><form:errors path="point" cssStyle="color: red"/></div>
  <div><form:button>送信</form:button></div>
</form:form>

</body>
</html>
