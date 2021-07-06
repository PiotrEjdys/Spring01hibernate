<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 06.07.2021
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form"
           uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="post"
           modelAttribute="student">
    <p> firstName:<form:textarea  path="firstName"  /></p>
    <p>lastName:<form:textarea path="lastName" /></p>
    <p>Male: <form:radiobutton path="gender" value="M"/></p>
    <p>Female: <form:radiobutton path="gender" value="F"/></p>
    <p>Country:<form:select path="country" items="${countries}" multiple="false"/></p>
    <p>  Notes: <form:textarea path="notes" value="notes"/></p>
    <p>Mailinglist: <form:checkbox value="true" path="mailingList" /></p>
    <p>Skills: <form:select path="programmingSkills" items="${skills}" multiple="true"/></p>
    <p>Hobbies <form:checkboxes path="hobbies" items="${hobbies}"/></p>
    <p> <input type="submit" value="Save"></p>
<%--    Mailinglist false: <form:checkbox value="false" path="mailingList" />--%>
</form:form>


</body>
</html>
