<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ze.liu
  Date: 2015/2/26
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
welcome ${loginUser.name}

<form action="${pageContext.request.contextPath}/order/create">
    <button>下单</button>
</form>

<table>
    <thead>
    <th>订单号</th>
    <th>用户id</th>
    </thead>
    <tbody>
    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.orderNo}</td>
            <td>${order.userId}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
