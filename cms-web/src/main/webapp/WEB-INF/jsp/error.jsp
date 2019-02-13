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
    <meta charset="utf-8">
    <title>异常</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"
          charset="utf-8">
    <script src="<%=request.getContextPath()%>/resources/layui/layui.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/resources/cms/js/admin/add.js" charset="utf-8"></script>
</head>
<body>
<div id="container">
    <div id="error">
        <span><i class="layui-icon layui-icon-face-surprised" style="font-size: 100px; color: red;"></i>  发现错误:</span>
        <div id="message"><span class="errorContainer">${ex.message}</span></div>
        <div id="upPage"><a href="javascript:history.go(-1)">返回上一页</a></div>
    </div>
</div>
</body>
</html>
