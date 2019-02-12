layui.use(['form', 'laydate','layer'], function () {
    var form = layui.form
        , laydate = layui.laydate
        ,layer=layui.layer
        , $ = layui.$;

    var index = parent.layer.getFrameIndex(window.name);

    laydate.render({
        elem: '#bornDate'
    });

    form.verify({
        username: function(value, item){ //value：表单的值、item：表单的DOM对象
            if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                return '用户名不能有特殊字符';
            }
            if(/(^\_)|(\__)|(\_+$)/.test(value)){
                return '用户名首尾不能出现下划线\'_\'';
            }
            if(/^\d+\d+\d$/.test(value)){
                return '用户名不能全为数字';
            }
        }
        ,pass: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });

    form.on('submit(submit)', function(data){
        parent.layer.close(index);
    });

});