<%--
  Created by IntelliJ IDEA.
  User: natalie
  Date: 2018/2/2
  Time: 下午2:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>success</title>
</head>
<body>
    <h4>success page</h4>
    <shiro:user>
        welcome <shiro:principal/> !
    </shiro:user>
    <a href="user.jsp">user</a>
    <a href="shiro/logout.do">log out</a>
</body>
</html>
