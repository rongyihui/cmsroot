layui.define(['laypage', 'form', 'layer', 'table'], function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , table = layui.table
        , laypage = layui.laypage
        , form = layui.form;

    /**
     * 将error异常弹出
     * @param datas error页面数据对象
     */
    var getErrorPage = function (datas) {
        layer.msg(datas.message, {
            icon: 2,
            time: 3000
        });
    };


    /**
     * 检查是否选择了数据
     * @param data 选择的数据data
     * @param activeMsg 未选择数据需要显示的信息
     * */
    var checkActive = function (data, activeMsg) {
        if (data.length == 0) {
            layer.msg(activeMsg);
            return;
        }
    }
    /**
     * layui 复选框缺陷，只会选择一个，通过以下方式处理
     * @param name input的name值
     * @returns {string}
     */
    var getCheckData = function (name) {
        var objIds = "";
        var objIdsObj = $('input[name="' + name + '"]');
        var first = true;
        for (k in objIdsObj) {
            if (objIdsObj[k].checked) {
                if (first) {
                    objIds = objIdsObj[k].value;
                    first = false;
                } else {
                    objIds = objIds + "," + objIdsObj[k].value;
                }
            }
        }
        return objIds;
    }

    var obj = {
        /**
         * 获取分页数据
         * @param url
         * @param dataObj 分页数据
         * @param array checkbox选中数据
         *
         * */
        getPageData: function (url, dataObj, array) {
            var arr = url.split("/");
            var prop = arr[arr.length - 1];
            $.get(url, dataObj, function (res) {
                laypage.render({
                    elem: prop + 'Page' //注意，这里的 test1 是 ID，不用加 # 号
                    , count: res.count
                    , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
                    , limit: dataObj.limit
                    , curr: dataObj.page
                    , limits: [15, 30, 60, 90]
                    , jump: function (obj, first) {
                        $('#' + prop + 's').empty();
                        layui.each(res.data, function (index, item) {
                            $('#' + prop + 's').append('<input type="checkbox" name="' + prop + 'Ids" title="' + item.name + '" value="' + item.id + '">');

                        });
                        if (array) {
                            for (var i = 0; i < array.length; i++) {
                                $('input[type=checkbox][name=' + prop + 'Ids][value=' + array[i] + ']').attr('checked', true);
                            }
                        }
                        form.render('checkbox');
                        if (!first) {
                            dataObj.page = obj.curr;
                            dataObj.limit = obj.limit;
                            obj.getPageData(url, dataObj);
                        }
                    }
                });
            });
        }
        /**
         *  add添加操作
         *  @param index 表示父类标识，方便关闭父类窗口
         *  @param url add操作需要添加的地址
         * */
        , submitData: function (url, index, type) {
            form.on('submit(submit)', function (data) {
                data.field.roleIds = getCheckData('roleIds');
                data.field.groupIds = getCheckData('groupIds');
                $.ajax({
                    type: type,
                    url: url,
                    data: data.field,
                    success: function (datas) {
                        if (datas === 'success') {
                            parent.layer.close(index);
                            /*刷新tab*/
                            parent.location.reload();
                        } else {
                            /*显示异常界面*/
                            getErrorPage(datas);
                        }
                    }
                });
                return false;
            });
        }
        /**
         * @param url 需要删除的url
         * @param data 需要删除的行数据
         */
        , submitDelData: function (url, data, msg) {
            layer.confirm(msg, function (index) {
                $.ajax({
                    type: 'delete'
                    , url: url + '/' + data.id
                    , success: function (datas) {
                        if (datas === 'success') {
                            //弹窗关闭
                            layer.close(index);
                            parent.location.reload();
                            layer.msg('已删除!', {
                                icon: 1,
                                time: 3000
                            });
                        } else {
                            getErrorPage(datas);
                        }
                    }
                });
            });
        }
        /**
         * 为list触发头工具栏事件
         * @param url url地址  类似：/cms/admin/group格式
         * @param tableId  table标签的id值 类似：id="u_table"
         * @param msg list页面没有选择，传递当前list名称  类似：用户组
         * @returns {{deleteData: deleteData, updateData: updateData, getCheckLength: getCheckLength, addData: addData, isAll: isAll}}
         */
        , submitInputData: function (url, tableId, msg) {
            var checkStatus = table.checkStatus(tableId)
                , data = checkStatus.data
                , active = {
                addData: function () {
                    /**
                     * 弹窗添加
                     * */
                    obj.showLayer(url + '/add');
                }
                , updateData: function () {
                    /**
                     * 弹窗修改
                     * */
                    checkActive(data, '请选择需要修改的' + msg);
                    var gid = data[0].id;
                    obj.showLayer(url + '/update/' + gid);

                }
                , deleteData: function () {
                    checkActive(data, '请选择需要删除的' + msg);
                    var name = data[0].name || data[0].nickname;
                    obj.submitDelData(url, data[0], '确认删除: ' + name + ' 吗?');
                }
                , getCheckLength: function () { //获取选中数目
                    layer.msg('选中了：' + data.length + ' 个');
                }
                , isAll: function () { //验证是否全选
                    layer.msg(checkStatus.isAll ? '全选' : '未全选')
                }
            };
            return active;
        }
        /**
         *  行工具栏方法实现
         * @param url 类似：/cms/admin/group
         * @param filterName table标签中lay-filter=""名称 类似：lay-filter="table_filter"
         */
        , listToolData: function (url, filterName) {
            table.on('tool(' + filterName + ')', function (tr) {
                var data = tr.data;
                if (tr.event === 'detail') {
                    obj.showLayer(url + '/' + data.id);
                } else if (tr.event === 'del') {
                    var name = data.name || data.nickname;
                    obj.submitDelData(url, data, '确认删除: ' + name + ' 吗?');
                } else if (tr.event === 'edit') {
                    var uid = data.id;
                    obj.showLayer(url + '/update/' + uid);
                } else if (tr.event === 'delAll') {
                    var uid = data.id;
                    var name = data.name || data.nickname;
                    obj.submitDelData(url + '/clean/', data, '确认清空' + name + ' 吗?');
                }
            });
        }
        /**
         * 处理XXXInput的弹窗
         * @param url Input的url地址
         */
        , showLayer: function (url) {
            layer.open({
                type: 2,
                area: ['70%', '80%'],
                fixed: false, //不固定
                maxmin: true,
                content: url
            });
        }
    };

    exports('cmsCore', obj);
});
