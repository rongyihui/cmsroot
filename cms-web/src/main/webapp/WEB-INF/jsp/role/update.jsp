<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        layui.use(['form', 'layer', 'element','cmsCore'], function () {
            var form = layui.form
                , layer = layui.layer
                , element = layui.element
                , cmsCore = layui.cmsCore
                , $ = layui.$;

            var index = parent.layer.getFrameIndex(window.name);

            cmsCore.submitData('/cms/admin/role',index,'put');

            form.val("fromUpdate", {
                "id": '${role.id}'
                , "name": '${role.name}'
                , "roleType": '${role.roleType}'
            })
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改用户角色：${role.name}</legend>
</fieldset>
<div style="margin-left: 20px;width:90%">
    <form class="layui-form layui-form-pane" lay-filter="fromUpdate">
        <input type="hidden" name="id">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户角色名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="name" lay-verify="required" placeholder="请输入用户角色名称"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户状态</label>
            <div class="layui-input-block">
                <c:forEach items="${types}" var="t">
                    <input type="radio" name="roleType" value="${t}" title="${t}">
                </c:forEach>
            </div>
        </div>
        <div class="layui-form-item roles">
            <div align="center">
                <button class="layui-btn" lay-submit="" lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>