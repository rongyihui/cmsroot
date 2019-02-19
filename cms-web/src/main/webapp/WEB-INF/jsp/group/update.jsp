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
        layui.use(['form', 'laydate', 'layer', 'util', 'element','cmsCore'], function () {
            var form = layui.form
                , laydate = layui.laydate
                , layer = layui.layer
                , element = layui.element
                , util = layui.util
                , cmsCore = layui.cmsCore
                , $ = layui.$;

            var index = parent.layer.getFrameIndex(window.name);

            laydate.render({
                elem: '#bornDate'
            });

            form.verify({
                username: function (value, item) { //value：表单的值、item：表单的DOM对象
                    if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                        return '用户名不能有特殊字符';
                    }
                    if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                        return '用户名首尾不能出现下划线\'_\'';
                    }
                    if (/^\d+\d+\d$/.test(value)) {
                        return '用户名不能全为数字';
                    }
                }
                , pass: [
                    /^[\S]{6,12}$/
                    , '密码必须6到12位，且不能出现空格'
                ]
            });

            form.on('submit(submit)', function (data) {
                parent.layer.close(index);
                parent.location.reload();
            });

            form.val("fromUpdate", {
                "id": '${user.id}'
                , "username": '${user.username}'
                , "password": '${user.password}'
                , "nickname": '${user.nickname}'
                , "status": '${user.status}'
                , "bornDate": layui.util.toDateString('${user.bornDate}', 'yyyy-MM-dd')
                , "phone": '${user.phone}'
                , "email": '${user.email}'
            })
            var gDataObj = {
                page: 1,//当前页面
                limit: 15
            };
            var rDataObj = {
                page: 1,//当前页面
                limit: 15
            };

            var array = new Array();
            <c:forEach items="${user.roleIds}" var="r">
            array.push(${r});
            </c:forEach>
            cmsCore.getPageData('/cms/admin/role',rDataObj,array);

            array.length = 0;
            <c:forEach items="${user.groupIds}" var="r">
            array.push(${r});
            </c:forEach>
            cmsCore.getPageData('/cms/admin/group',gDataObj,array);
        });
    </script>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>修改用户</legend>
</fieldset>
<div style="margin-left: 20px;width:90%">
    <form class="layui-form layui-form-pane" action="/cms/admin/user" method="post" lay-filter="fromUpdate">
        <input type="hidden" name="_method" value="PUT">
        <input type="hidden" name="id">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">用户账号</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" lay-verify="required|username" readonly placeholder="请输入账号"
                           autocomplete="off"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">用户名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="nickname" lay-verify="required" placeholder="请输入名称" autocomplete="off"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" lay-verify="required|pass" placeholder="请输入密码"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">出生日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="bornDate" id="bornDate" lay-verify="required|date" placeholder="yyyy-MM-dd"
                           autocomplete="off" class="layui-input">
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
            <label class="layui-form-label">用户状态</label>
            <div class="layui-input-block">
                <input type="radio" name="status" value="-1" title="黑名单用户">
                <input type="radio" name="status" value="0" title="管理员">
                <input type="radio" name="status" value="1" title="正常用户">
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
                <div class="layui-colla-content layui-show">
                    <div id="groups"></div>
                    <div id="groupPage"></div>
                </div>
            </div>
        </div>
        <div class="layui-form-item groups">
            <div align="center">
                <button class="layui-btn" lay-submit="" lay-filter="submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>