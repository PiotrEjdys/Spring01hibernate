<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 06.07.2021
  Time: 12:02
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
<%--<form method="post">--%>
<%--    <input name="login" type="text" placeholder="login">--%>
<%--    <input name="email" type="text" placeholder="email">--%>
<%--    <input name="password" type="password" placeholder="password">--%>
<%--    <input type="submit" value="save">--%>
<%--</form>--%>
<form:form method="post"
           modelAttribute="person">
    <p> login<form:input path="login" /></p>
    <p> email<form:input path="email" /></p>
    <p> password<form:input path="password" /></p>
    <p><input type="submit" value="Save"></p>

</form:form>

</body>
</html>
