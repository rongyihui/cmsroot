layui.define(['laypage', 'form'], function (exports) {
    var $ = layui.$
        , laypage = layui.laypage
        , form = layui.form;
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
                                $('input[type=checkbox][name='+prop+'Ids][value=' + array[i] + ']').attr('checked', true);
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
    };

    exports('cmsCore', obj);
});
