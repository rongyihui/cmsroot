<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>cms</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"
          charset="utf-8">
    <script src="<%=request.getContextPath()%>/resources/layui/layui.js" charset="utf-8"></script>
    <script>
        layui.use(['table', 'util', 'element'], function () {
            var table = layui.table;
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>${user.nickname}</legend>
</fieldset>
<div style="margin-left: 20px;width: 90%">
    <table class="layui-table" lay-size="lg">
        <colgroup>
            <col width="190">
            <col>
        </colgroup>
        <tr>
            <td>用户账号：</td>
            <td>${user.username}</td>
        </tr>
        <tr>
            <td>用户名称：</td>
            <td>${user.nickname}</td>
        </tr>
        <tr>
            <td>用户状态：</td>
            <td>${user.status}</td>
        </tr>
        <tr>
            <td>出生日期：</td>
            <td>${user.bornDate}</td>
        </tr>
        <tr>
            <td>首次创建日期：</td>
            <td>${user.createDate}</td>
        </tr>
        <tr>
            <td>电话：</td>
            <td>${user.phone}</td>
        </tr>
        <tr>
            <td>邮件：</td>
            <td>${user.email}</td>
        </tr>
        <tr>
            <td>用户角色：</td>
            <td>
                <div class="layui-btn-container">
                <c:forEach items="${rs}" var="r">
                    <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-normal">${r.name}</button>
                </c:forEach>
                </div>
            </td>
        </tr>
        <tr>
            <td>用户组：</td>
            <td>
                <div class="layui-btn-container">
                    <c:forEach items="${gs}" var="g">
                        <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-normal">${g.name}</button>
                    </c:forEach>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>