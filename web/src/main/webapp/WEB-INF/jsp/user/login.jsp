<%--
  Created by IntelliJ IDEA.
  User: ze.liu
  Date: 2014/5/28
  Time: 10:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form action="<%=request.getContextPath()%>/login" method="post">
    name:<input type="text" name="name"/><br/>
    password:<input type="password" name="password"/>
    <button>提交</button>
</form>
</body>
</html>
