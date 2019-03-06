<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>树</title>
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

            $.get("/cms/admin/tree","", function (res){
                tree({
                    elem: '#ctree' //传入元素选择器
                    ,nodes:res
                    ,click: function(node){
                        console.log(node)
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="layui-row layui-col-space10">
    <div class="layui-col-xs2">
        <ul id="ctree"></ul>
    </div>
    <div class="layui-col-xs10">
        <iframe frameborder="0" scrolling="yes"></iframe>
    </div>
</div>
</body>
</html>
