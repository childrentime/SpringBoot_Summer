<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>系统错误，请联系网站管理员</p>
<%--"${pageContext.request.contextPath} 绝对路径 取出localhost:8080/tmall 所以可以返回主页--%>
<a href="${pageContext.request.contextPath}">返回主页</a>
</body>
</html>
