layui.config({
    /*定义js的路径*/
    base: '/cms/resources/cms/js/'
}).extend({
    /*为自定义js设置别名*/
    cmsCore: 'cms.core'
});
layui.use(['cmsCore','table'], function () {
    var table = layui.table
        , cmsCore = layui.cmsCore
        , $ = layui.$;

    table.render({
        elem: '#u_table'
        , url: '/cms/admin/role/'
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
            , {field: 'name', width: 100, title: '角色名称'}
            , {field: 'roleType', width: 350, title: '角色类型'}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 280, align: 'center'}
        ]]
        , page: {groups: 8}
    });

    //触发头工具栏事件
    $('.layui-btn-group .layui-btn').on('click', function () {
        var type = $(this).data('type');
        var active = cmsCore.submitInputData('/cms/admin/role','u_table','用户角色')[type];
        active? active.call(this) : '';
    });
    //监听行工具事件
    cmsCore.listToolData('/cms/admin/role','table_filter');
});