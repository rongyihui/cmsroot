<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%--头部信息--%>
    <div class="layui-header">
        <div class="layui-logo">cms管理</div>
        <ul class="layui-nav layui-layout-left" lay-filter="demo">
            <li class="layui-nav-item"><a href="/admin/user">用户管理</a></li>
            <li class="layui-nav-item"><a href="javascript:;">角色管理</a></li>
            <li class="layui-nav-item"><a href="javascript:;">信息管理</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    管理员
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>
    <%--左侧--%>
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="navDemo">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">所有用户</a>
                    <dl class="layui-nav-child" >
                        <dd><a href="javascript:;">用户列表</a></dd>
                        <dd><a href="javascript:;">角色列表</a></dd>
                        <dd><a href="javascript:;">组列表</a></dd>
                        <dd><a href="javascript:">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">商品列表</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab layui-tab-brief" lay-allowClose="true" lay-filter="work">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="1">网站设置</li>
            </ul>
            <div class="layui-tab-content" style="height: 100px;">
                <div class="layui-tab-item layui-show">1</div>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 2019-2010
    </div>
</div>
<script src="<%=request.getContextPath()%>/resources/layui/layui.js" charset="utf-8"></script>
<script src="<%=request.getContextPath()%>/resources/cms/admin/index.js"></script>
</body>
</html>