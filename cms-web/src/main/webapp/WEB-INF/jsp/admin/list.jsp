<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/1/19
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/layui/css/layui.css" media="all">
</head>
<body>

<table class="layui-hide" id="demo" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="<%=request.getContextPath()%>/resources/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
        var laydate = layui.laydate //日期
            ,laypage = layui.laypage //分页
            ,table = layui.table //表格
            ,upload = layui.upload //上传

        table.render({
            elem: '#demo'
            ,height: 520
            ,url: '/cms/admin/user' //数据接口
            ,title: '部门列表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,parseData: function(res){
                return {
                    "code": 0,
                    "data":res.data
                };
            }
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left', totalRowText: '合计：'}
                ,{field: 'id', title: 'ID', width:60, sort: true, fixed: 'left', totalRow: true}
                ,{field: 'username', title: '用户账号', width:80}
                ,{field: 'password', title: '密码', width: 80}
                ,{field:'nickname', title: '用户名称', width:100}
                ,{field: 'email', title: '邮箱', width:180}
                ,{field: 'phone', title: '电话', width:130}
                ,{field: 'status', title: '用户状态', width:100}
                ,{field: 'bornDate', title: '出生日期', width:120}
                ,{field: 'createDate', title: '创建日期', width:120}
            ]]
        });
    });
</script>
</body>
</html>
