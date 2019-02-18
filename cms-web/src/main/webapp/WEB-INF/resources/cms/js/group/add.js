layui.use(['form', 'laydate','layer','element'], function () {
    var form = layui.form
        , layer=layui.layer
        , element =layui.element
        , $ = layui.$;

    var index = parent.layer.getFrameIndex(window.name);

    form.verify({
        name: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '组名称不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '组名称首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '组名称不能全为数字';
            }
        }
    });

    form.on('submit(submit)', function(data){
        parent.layer.close(index);
        /*刷新tab*/
        parent.location.reload();
    });

});