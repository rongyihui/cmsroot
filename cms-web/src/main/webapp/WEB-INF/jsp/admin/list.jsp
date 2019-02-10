<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>部门列表</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/layui/css/layui.css" media="all">
    <script src="<%=request.getContextPath()%>/resources/layui/layui.js" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/resources/cms/js/admin/list.js" charset="utf-8"></script>
</head>
<body>
<script type="text/html" id="toolBar">
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-sm" data-type="addData">增加</button>
        <button class="layui-btn layui-btn-sm" data-type="updateData">编辑</button>
        <button class="layui-btn layui-btn-sm" data-type="deleteData">删除</button>
        <button class="layui-btn layui-btn-sm" data-type="getCheckData">获取选中数据</button>
        <button class="layui-btn layui-btn-sm" data-type="getCheckLength">选中数目</button>
        <button class="layui-btn layui-btn-sm" data-type="isAll">是否全选</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<table class="layui-hide" id="u_table" lay-filter="table_filter"></table>
</body>
</html>
