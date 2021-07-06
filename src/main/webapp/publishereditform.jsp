<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 06.07.2021
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post"
           modelAttribute="publisher">
    <p> title<form:input path="name" /></p>
    <p><input type="submit" value="Save"></p>
</form:form>
</body>
</html>
