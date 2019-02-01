layui.use(['element', 'laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function () {
    var laydate = layui.laydate //日期
        , laypage = layui.laypage //分页
        , table = layui.table //表格
        , upload = layui.upload //上传
        , element = layui.element
        , $ = layui.$;

    /*侧边导航，生成选项卡*/
    $(".layui-nav-child dd").on("click", function () {
        var titleTab = $(this).text();
        var li = $(".layui-tab-title li");
        var layId = new Date().getTime();
        var hasTab = li.text().indexOf(titleTab);
        if (hasTab < 0) {
            element.tabAdd('work', {
                title: titleTab
                , content: '<table class="layui-hide" id="tableUser"></table>'
                , id: layId //实际使用一般是规定好的id，这里以时间戳模拟下
            })
        }
    });

    /*记录选项卡位置，通过hash定位*/
    element.on('tab(work)', function () {
        location.hash = 'tab=' + $(this).attr('lay-id');
    });

    //Hash地址的定位
    var layid = location.hash.replace(/^#tab/, '');
    element.tabChange('work', layid);
});