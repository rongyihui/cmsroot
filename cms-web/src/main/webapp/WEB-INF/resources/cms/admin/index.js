layui.use(['element','laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element', 'slider'], function(){
    var laydate = layui.laydate //日期
        ,laypage = layui.laypage //分页
        ,table = layui.table //表格
        ,upload = layui.upload //上传
        ,element = layui.element
        ,$ = layui.$

    element.on('tab(work)', function(elem){
        location.hash = $(this).attr('lay-id');
    });
    //Hash地址的定位
    var layid = location.hash;
    element.tabChange('work', layid);

    table.render({
        elem: '#demo'
        ,height: 520
        ,url: '/department/' //数据接口
        ,title: '部门列表'
        ,page: true //开启分页
        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,totalRow: true //开启合计行
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
            ,{field: 'depName', title: '部门名称', width:80}
            ,{field: 'level', title: '等级', width: 90, sort: true, totalRow: true}
        ]]
    });
});