<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <script type="text/javascript" src="scripts/wro/javaonly-base-scripts.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<a href="toFreemarker.do">toFreemarker</a>

<shiro:guest>
    welcome,please <a href="/shiro/login.do">login</a>
</shiro:guest>
</body>
</html>
