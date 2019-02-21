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
        layui.config({
            /*定义js的路径*/
            base: '/cms/resources/cms/js/'
        }).extend({
            /*为自定义js设置别名*/
            cmsCore: 'cms.core'
        });
        layui.use(['table','cmsCore'], function () {
            var table = layui.table
                , cmsCore = layui.cmsCore
                , $ = layui.$;

            $(".layui-table .layui-btn").on('click',function () {
                cmsCore.showLayer("/cms/admin/user/"+this.value);
            });

        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>${role.name}</legend>
</fieldset>
<div style="margin-left: 20px;width: 90%">
    <table class="layui-table" lay-size="lg">
        <colgroup>
            <col width="190">
            <col>
        </colgroup>
        <tr>
            <td>用户角色名称：</td>
            <td>${role.name}</td>
        </tr>
        <tr>
            <td>用户角色类型：</td>
            <td>${role.roleType}</td>
        </tr>
        <tr>
            <td>用户组用户：</td>
            <td>
                <div class="layui-btn-container">
                <c:forEach items="${users}" var="user">
                    <button value="${user.id}" class="layui-btn layui-btn-xs layui-btn-radius layui-btn-normal">${user.nickname}</button>
                </c:forEach>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>