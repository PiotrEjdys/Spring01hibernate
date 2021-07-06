<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 06.07.2021
  Time: 17:18
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
           modelAttribute="author">
    <p> title<form:input path="firstName" /></p>
    <p> description<form:input path="lastName" /></p>
    <p><input type="submit" value="Save"></p>
</form:form>

</body>
</html>
