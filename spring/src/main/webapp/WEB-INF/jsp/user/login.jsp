<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    name: <input name="name" type="text"/><br/>
    password: <input name="password" type="password"/><br/>
    <span style="color: red">${error}</span><br/>
    <button>login</button>
</form>
</body>
</html>