<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/register" method="post">
    name: <input name="name" type="text" /><br />
    password: <input name="password" type="password" /><br />
    <span style="color: red">${error}</span><br />
    <button>register</button>
</form>
</body>
</html>