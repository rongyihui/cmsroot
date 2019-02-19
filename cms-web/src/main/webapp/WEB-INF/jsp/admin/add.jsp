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
    <script src="<%=request.getContextPath()%>/resources/cms/js/admin/add.js" charset="utf-8"></script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>添加新用户</legend>
</fieldset>
<div style="margin-left: 20px;width:90%">
    <form class="layui-form layui-form-pane">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" value="" lay-verify="required|username" placeholder="请输入账号"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="nickname" value="" lay-verify="required" placeholder="请输入名称"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" value="" lay-verify="required|pass" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">出生日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="bornDate" value="" id="bornDate" lay-verify="required|date"
                           placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                    <input type="tel" name="phone" value="" lay-verify="required|phone" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" name="email" value="" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户状态</label>
            <div class="layui-input-block">
                <input type="radio" name="status" value="-1" title="黑名单用户">
                <input type="radio" name="status" value="0" title="管理员">
                <input type="radio" name="status" value="1" title="正常用户" checked>
            </div>
        </div>
        <div class="layui-collapse" style="margin-top: 10px">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">用户角色</h2>
                <div class="layui-colla-content layui-show">
                    <div id="roles"></div>
                    <div id="rolePage"></div>
                </div>
            </div>
        </div>
        <div class="layui-collapse" style="margin-top: 10px">
            <div class="layui-colla-item">
                <h2 class="layui-colla-title">用户组</h2>
                <div class="layui-colla-content layui-show" >
                    <div id="groups"></div>
                    <div id="groupPage"></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 10px">
            <div align="center">
                <button class="layui-btn" lay-submit="" lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>