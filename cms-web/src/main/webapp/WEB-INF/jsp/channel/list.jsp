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
    <script charset="utf-8">
        layui.use(['tree'], function () {
            var tree = layui.tree
                , $ = layui.$;

            $.get("/cms/admin/channel","", function (res){
                tree({
                    elem: '#demo' //传入元素选择器
                    ,nodes:res
                });
            });
        });
    </script>
</head>
<body>
<ul id="demo"></ul>
</body>
</html>
