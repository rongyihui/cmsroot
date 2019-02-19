layui.define(['laypage', 'form', 'layer','table'], function (exports) {
    var $ = layui.$
        , layer = layui.layer
        , table = layui.table
        , laypage = layui.laypage
        , form = layui.form;

    /**
     * 将error异常弹出
     * @param obj error页面数据
     */
    var getErrorPage = function (obj){
        layer.open({
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['70%', '80%'], //宽高
            content: obj
        });
    };

    /**
     * 处理XXXInput的弹窗
     * @param url Input的url地址
     */
    var showLayer = function(url) {
        layer.open({
            type: 2,
            area: ['70%', '80%'],
            fixed: false, //不固定
            maxmin: true,
            content: url
        });
    }

    /**
     * 检查是否选择了数据
     * @param data 选择的数据data
     * @param activeMsg 未选择数据需要显示的信息
     * */
    var checkActive =function(data,activeMsg){
        if (data.length==0){
            layer.msg(activeMsg);
            return;
        }
    }

    var obj = {
        /**
         * 获取分页数据
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
        , submitAddData: function (url, index) {
            form.on('submit(submit)', function (data) {
                $.ajax({
                    type: 'post',
                    url: url,
                    data: data.field,
                    success: function (obj) {
                        if (obj === 'success') {
                            parent.layer.close(index);
                            /*刷新tab*/
                            parent.location.reload();
                        } else {
                            /*显示异常界面*/
                            getErrorPage(obj);
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
        , submitDelData: function (url,data) {
            var name = data.name||data.nickname;
            layer.confirm('确认删除: ' + name+' 吗?', function (index) {
                $.ajax({
                    type: 'delete'
                    , url: url +'/'+ data.id
                    , success: function (datas) {
                        if (datas === 'success') {
                            //弹窗关闭
                            parent.layer.close(index);
                            parent.location.reload();
                            layer.msg('已删除!', {
                                icon: 1,
                                time: 3000
                            });
                        }else {
                            /*显示异常界面*/
                            getErrorPage(datas);
                        }
                    }
                });
                return false;
            });
        }
        /**
         * 为list触发头工具栏事件
         * @param url url地址  类似：/cms/admin/group格式
         * @param tableId  table标签的id值 类似：id="u_table"
         * @param msg list页面没有选择，传递当前list名称  类似：用户组
         * @returns {{deleteData: deleteData, updateData: updateData, getCheckLength: getCheckLength, addData: addData, isAll: isAll}}
         */
        ,submitInputData:function (url,tableId,msg) {
            var checkStatus = table.checkStatus(tableId)
                , data = checkStatus.data
                , active = {
                addData: function () {
                    /**
                     * 弹窗添加
                     * */
                    showLayer(url+'/add');
                }
                , updateData: function () {
                    /**
                     * 弹窗修改
                     * */
                    checkActive(data,'请选择需要修改的'+msg);
                    var gid = data[0].id;
                    showLayer(url+'/update/' + gid);

                }
                , deleteData: function () {
                    checkActive(data,'请选择需要删除的'+msg);
                    obj.submitDelData(url,data[0]);
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
        ,listToolData:function (url,filterName) {
            table.on('tool('+filterName+')', function (tr) {
                var data = tr.data;
                if (tr.event === 'detail') {
                    showLayer(url+ data.id);
                } else if (tr.event === 'del') {
                    obj.submitDelData(url,data);
                } else if (tr.event === 'edit') {
                    var uid = data.id;
                    showLayer(url + '/update/' + uid);
                }
            });
        }
    };

    exports('cmsCore', obj);
});
