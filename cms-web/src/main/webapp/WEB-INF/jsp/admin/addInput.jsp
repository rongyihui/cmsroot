<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>cms</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all"
          charset="utf-8">
    <script src="<%=request.getContextPath()%>/resources/layui/layui.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/resources/cms/js/admin/addInput.js" charset="utf-8"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加新用户</legend>
</fieldset>
<div align="center">
<form class="layui-form layui-form-pane" action="/cms/admin/user" method="post">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户账号</label>
            <div class="layui-input-inline">
                <input type="text" name="username" required lay-verify="required|username" placeholder="请输入账号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">用户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="nickname" required lay-verify="required" placeholder="请输入名称" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="text" name="bornDate" id="bornDate" lay-verify="required|date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline">
                <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div align="center">
            <button class="layui-btn" lay-submit="" lay-filter="submit">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</div>
</body>
</html>