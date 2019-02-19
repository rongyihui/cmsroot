layui.config({
    /*定义js的路径*/
    base: '/cms/resources/cms/js/'
}).extend({
    /*为自定义js设置别名*/
    cmsCore: 'cms.core'
});
layui.use(['table', 'layer', 'util', 'cmsCore'], function () {
    var table = layui.table
        , util = layui.util
        , layer = layui.layer
        , cmsCore = layui.cmsCore
        , $ = layui.$;

    table.render({
        elem: '#u_table'
        , url: '/cms/admin/user/'
        , toolbar: '#toolBar'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度
        , limit: 15
        , limits: [15, 30, 60, 90]
        , parseData: function (res) {
            return {
                "code": 0,
                "data": res.data,
                "count": res.count
            };
        }
        , cols: [[
            {type: 'numbers', fixed: 'left'}
            , {type: 'radio'}
            , {field: 'id', width: 80, title: 'ID', sort: true}
            , {field: 'username', width: 100, title: '用户账号'}
            , {field: 'password', width: 80, title: '密码', sort: true}
            , {field: 'nickname', width: 120, title: '用户名称'}
            , {field: 'email', width: 180, title: '邮箱'}
            , {field: 'phone', width: 150, title: '电话'}
            , {
                field: 'bornDate',
                width: 150,
                title: '出生日期',
                sort: true,
                templet: '<div>{{layui.util.toDateString(d.bornDate,\'yyyy-MM-dd\')}}</div>'
            }
            , {
                field: 'createDate',
                width: 150,
                title: '创建日期',
                sort: true,
                templet: '<div>{{layui.util.toDateString(d.createDate,\'yyyy-MM-dd\')}}</div>'
            }
            , {
            field: 'status',
                width: 110,
                title: '用户状态',
                sort: true,
                templet:'<div>' +
                    '{{# if (d.status==-1){ }}' +
                    '黑名单用户' +
                    '{{# }else if(d.status==0){ }}' +
                    '管理员' +
                    '{{# } else if (d.status==1){}}' +
                    '正常用户' +
                    '{{# } }}</div>'
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180, align: 'center'}
        ]]
        , page: {groups: 8}
    });

    //触发头工具栏事件
    $('.layui-btn-group .layui-btn').on('click', function () {
        var type = $(this).data('type');
        var active = cmsCore.submitInputData('/cms/admin/user','u_table','用户')[type];
        active ? active.call(this) : '';
    });
    //监听行工具事件
    cmsCore.listToolData('/cms/admin/user','table_filter');

});