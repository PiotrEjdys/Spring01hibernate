<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: piotr
  Date: 06.07.2021
  Time: 09:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<ul>
<c:forEach items="${books}" var="book">
    <li> ${book}<a href="/book/choose/${book.id}">Edit or delete</a> </li>
</c:forEach>

</ul>

</body>
</html>