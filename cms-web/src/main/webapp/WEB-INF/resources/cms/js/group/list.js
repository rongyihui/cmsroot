layui.use(['table', 'layer', 'util'], function () {
    var table = layui.table
        , util = layui.util
        , layer = layui.layer
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
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180, align: 'center'}
        ]]
        , page: {groups: 8}
    });
    /*checkbox监听选择框*/
    var tr;
    table.on('checkbox(table_filter)', function (obj) {
        //console.log(obj)
        tr = obj;
    });

    var active = {
        addData: function () {
            /**
             * 弹窗添加用户
             * */
            showLayer('/cms/admin/group/add');
        }
        , updateData: function () {
            /**
             * 弹窗修改用户
             * */
            var checkStatus = table.checkStatus('u_table')
                , data = checkStatus.data;
            checkActive(data,'请选择需要修改的用户');
            var uid = data[0].id;
            showLayer('/cms/admin/group/update/' + uid);

        }
        , deleteData: function () {
            var checkStatus = table.checkStatus('u_table')
                , data = checkStatus.data;
            checkActive(data,'请选择需要删除的用户');
            deleteMethod(data[0]);
        }
        , getCheckLength: function () { //获取选中数目
            var checkStatus = table.checkStatus('u_table')
                , data = checkStatus.data;
            layer.msg('选中了：' + data.length + ' 个');
        }
        , isAll: function () { //验证是否全选
            var checkStatus = table.checkStatus('u_table');
            layer.msg(checkStatus.isAll ? '全选' : '未全选')
        }
    };
    /*检查是否选择了数据*/
    function checkActive(data,activeMsg){
        if (data.length==0){
            layer.msg(activeMsg);
            return;
        }
    }

    //触发头工具栏事件
    $('.layui-btn-group .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //监听行工具事件
    table.on('tool(table_filter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            showLayer('/cms/admin/group/'+ data.id);
        } else if (obj.event === 'del') {
            deleteMethod(data);
        } else if (obj.event === 'edit') {
            var uid = data.id;
            showLayer('/cms/admin/group/update/' + uid);
        }
    });

    function showLayer(content) {
        layer.open({
            type: 2,
            area: ['70%', '80%'],
            fixed: false, //不固定
            maxmin: true,
            content: content
        });
    }

    function deleteMethod(data) {
        layer.confirm('确认删除:' + data.nickname, function (index) {
            $.ajax({
                type: 'delete'
                , url: '/cms/admin/group/' + data.id
                , success: function (data) {
                    //弹窗关闭
                    tr.del();
                    layer.close(index);
                    layer.msg('已删除!', {
                        icon: 1,
                        time: 3000
                    });
                }
            });
        });
    }
});