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
<form action="<%=request.getContextPath()%>/json/test" method="post">
    <input type="text" hidden="true" value="[1:hello,2:ok]"/>
    <input type="submit" value="ok">
</form>
</body>
</html>
