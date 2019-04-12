<%--
  Created by IntelliJ IDEA.
  User: natalie
  Date: 2018/2/2
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <h4>login jsp</h4>
    <shiro:guest>
        Welcome,please login
        <form action="/shiro/login.do" method="post">
            username:<input type="text" name="username">
            password:<input type="text" name="password">
            <input type="submit" value="submit">
        </form>
    </shiro:guest>
</body>
</html>
