layui.use(['table', 'layer', 'util'], function () {
    var table = layui.table
        , util = layui.util
        , layer = layui.layer
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
            , {type: 'checkbox'}
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
                templet: '<div>{{layui.util.toDateString(d.bornDate,\'yyyy-MM-dd\')}}</div>"}</div>'
            }
            , {
                field: 'createDate',
                width: 150,
                title: '创建日期',
                sort: true,
                templet: '<div>{{layui.util.toDateString(d.createDate,\'yyyy-MM-dd\')}}</div>"}</div>'
            }
            , {field: 'status', width: 110, title: '用户状态', sort: true}
            , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 180, align: 'center'}
        ]]
        , page: true
    });

    /*checkbox监听选择框*/
    table.on('checkbox(table_filter)', function (obj) {
        console.log(obj)
    });

    var active = {
        addData: function () {
            /**
             * @todo 弹窗添加用户
             * */
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '/cms/admin/user/addInput'
            });
        }
        , updateData: function () {
            /**
             * @todo 弹窗修改用户
             * */
        }
        , deleteData: function () {
            /**
             * @todo 弹窗删除用户
             * */
        }
        , getCheckData: function () { //获取选中数据
            var checkStatus = table.checkStatus('u_table')
                , data = checkStatus.data;
            layer.alert(JSON.stringify(data));
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
    //触发头工具栏事件
    $('.layui-btn-group .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
    //监听行工具事件
    table.on('tool(table_filter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.open({
                type: 2,
                area: ['700px', '450px'],
                fixed: false, //不固定
                maxmin: true,
                content: '/cms/admin/user/addInput'
            });
        } else if (obj.event === 'del') {
            layer.confirm('确认删除:' + data.nickname, function (index) {
                $.ajax({
                    type: 'delete'
                    , url: '/cms/admin/user/' + data.id
                    , success: function (data) {
                        //这里获取到数据执行显示

                        //删除对应行（tr）的DOM结构，并更新缓存
                        obj.del();
                        //弹窗关闭
                        layer.close(index);
                        layer.msg('已删除!', {
                            icon: 1,
                            time: 3000
                        });
                    }
                });
            });
        } else if (obj.event === 'edit') {
            layer.alert('编辑行：<br>' + JSON.stringify(data))
        }
    });
});