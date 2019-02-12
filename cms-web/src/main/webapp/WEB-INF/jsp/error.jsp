<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/12
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发现异常</title>
</head>
<body>
<div id="container">
    <div id="error">
        <span>发现错误</span>
        <div id="message"><span class="errorContainer">${ex.message}</span></div>
        <div id="upPage"><a href="javascript:history.go(-1)">返回上一页</a></div>
    </div>
</div>
</body>
</html>
