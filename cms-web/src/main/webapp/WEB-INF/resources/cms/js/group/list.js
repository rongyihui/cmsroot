layui.config({
    /*定义js的路径*/
    base: '/cms/resources/cms/js/'
}).extend({
    /*为自定义js设置别名*/
    cmsCore: 'cms.core'
});
layui.use(['util', 'cmsCore','table'], function () {
    var util = layui.util
        , table = layui.table
        , cmsCore = layui.cmsCore
        , $ = layui.$;

    table.render({
        elem: '#u_table'
        , url: '/cms/admin/group/'
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
            , {field: 'name', width: 100, title: '组名称'}
            , {field: 'intro', width: 350, title: '组介绍'}
            , {
                field: 'createDate',
                width: 150,
                title: '创建日期',
                sort: true,
                templet: '<div>{{layui.util.toDateString(d.createDate,\'yyyy-MM-dd\')}}</div>'
            }
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 280, align: 'center'}
        ]]
        , page: {groups: 8}
    });

    //触发头工具栏事件
    $('.layui-btn-group .layui-btn').on('click', function () {
        var type = $(this).data('type');
        var active = cmsCore.submitInputData('/cms/admin/group','u_table','用户组')[type];
        active? active.call(this) : '';
    });
    //监听行工具事件
    cmsCore.listToolData('/cms/admin/group','table_filter');
});