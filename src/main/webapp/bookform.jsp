<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 06.07.2021
  Time: 14:15
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
           modelAttribute="book">

    <p> title<form:input path="title" /> <form:errors path ="title"/></p>
    <p> description<form:input path="description" /> <form:errors path ="description"/></p>
    <p> rating<form:input path="rating" /> <form:errors path ="rating"/></p>
    <p>pages <form:input path="pages"/> <form:errors path ="pages"/></p>
    <p>Publisher:<form:select path="publisher" itemLabel="name" itemValue="id" items="${publishers}" multiple="false"/> <form:errors path ="publisher"/></p>
    <p>Author:<form:select path="authors" itemLabel="firstName" itemValue="id" items="${authors}" multiple="false"/><form:errors path ="authors"/></p>
    <p><input type="submit" value="Save"></p>
</form:form>

</body>
</html>
