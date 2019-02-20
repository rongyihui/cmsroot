layui.config({
    /*定义js的路径*/
    base: '/cms/resources/cms/js/'
}).extend({
    /*为自定义js设置别名*/
    cmsCore: 'cms.core'
});
layui.use(['form', 'laydate','element', 'cmsCore'], function () {
    var form = layui.form
        , element =layui.element
        , cmsCore = layui.cmsCore
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

    cmsCore.submitData('/cms/admin/group',index,'post');
});