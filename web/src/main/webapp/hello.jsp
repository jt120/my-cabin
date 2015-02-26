<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ze.liu
  Date: 2014/12/1
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:set value="zhang/san ni" var="pname"></c:set>
<%
    String pNm = (String) pageContext.getAttribute("pname");
    String[] tmp = null;
    if (pNm != null) {
        tmp = pNm.split("/");
    }
    String reName = pNm;
    if (tmp != null && tmp.length == 2) {
        reName = tmp[1] + "/" + tmp[0];
    }

%>
<h1><%=reName%></h1>

</body>
</html>
