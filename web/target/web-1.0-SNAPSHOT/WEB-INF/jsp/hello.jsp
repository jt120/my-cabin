<%--
  Created by IntelliJ IDEA.
  User: ze.liu
  Date: 2014/5/28
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<h1>hello</h1>
<c:if test="${name == null}">
    <p>ci if </p>
</c:if>
<c:choose>

    <c:when test="${name == null && age == 13}">
        <p>in when</p>
    </c:when>
    <c:otherwise>
        <p>not in </p>
    </c:otherwise>
</c:choose>
</body>
</html>
