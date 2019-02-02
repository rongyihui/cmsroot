layui.use(['form', 'element'], function () {
    var form = layui.form //表格
        , element = layui.element
        , $ = layui.$;

    /*
     * @todo 左侧菜单事件
     * 如果有子级就展开，没有就打开frame
     */
    $('.layui-nav-child li a').click(function (event) {
        var url = $(this).attr('_href');
        var title = $(this).html();
        var index = $('.layui-nav-child li a').index($(this));
        for (var i = 0; i < $('.weIframe').length; i++) {
            if ($('.weIframe').eq(i).attr('tab-id') == index + 1) {
                tab.tabChange(index + 1);
                event.stopPropagation();
                return;
            }
        }
        tab.tabAdd(title, url, index + 1);
        tab.tabChange(index + 1);
        event.stopPropagation(); //不触发任何前辈元素上的事件处理函数
    });


    /**
     * tab触发事件：增加、删除、切换
     * @type {{tabDelete: tabDelete, tabDeleteAll: tabDeleteAll, tabChange: tabChange, tabAdd: tabAdd}}
     */
    var tab = {
        tabAdd: function (title, url, id) {
            //判断当前id的元素是否存在于tab中
            var li = $("#tabTip li[lay-id=" + id + "]").length;
            //console.log(li);
            if (li > 0) {
                //tab已经存在，直接切换到指定Tab项
                //console.log(">0");
                element.tabChange('nav_tab', id); //切换到：用户管理
            } else {
                //该id不存在，新增一个Tab项
                //console.log("<0");
                element.tabAdd('nav_tab', {
                    title: title,
                    content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="weIframe"></iframe>',
                    id: id
                });
                //当前窗口内容
                setStorageMenu(title, url, id);

            }
            CustomRightClick(id); //绑定右键菜单
            IFrameWH(); //计算框架高度

        },
        tabDelete: function (id) {
            element.tabDelete("wenav_tab", id); //删除
            removeStorageMenu(id);

        },
        tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('wenav_tab', id);
        },
        tabDeleteAll: function (ids) { //删除所有
            $.each(ids, function (i, item) {
                element.tabDelete("wenav_tab", item);
            })
            sessionStorage.removeItem('menu');
        }
    };

    /**
     * 重新计算iframe高度
     * @constructor
     */
    function IFrameWH() {
        var h = $(window).height() - 194;
        console.log(h);
        $("iframe").css("height", h + "px");
    }

    /**
     * 浏览器大小调整，进行iframe调整
     */
    $(window).resize(function () {
        IFrameWH();
    });

    //本地存储记录所有打开的窗口
    function setStorageMenu(title, url, id) {
        var menu = JSON.parse(sessionStorage.getItem('menu'));
        if (menu) {
            var deep = false;
            for (var i = 0; i < menu.length; i++) {
                //如果本地存在，替换修改的内容
                if (menu[i].id == id) {
                    deep = true;
                    menu[i].title = title;
                    menu[i].url = url;
                    menu[i].id = id;
                }
            }
            //本地不存在，添加到本地
            if (!deep) {
                menu.push({
                    title: title,
                    url: url,
                    id: id
                })
            }
        } else {
            //第一次储存到本地
            var menu = [{
                title: title,
                url: url,
                id: id
            }]
        }
        sessionStorage.setItem('menu', JSON.stringify(menu));
    }

    /**
     * @todo 监听右键事件,绑定右键菜单
     * @param id
     * @constructor
     */
    function CustomRightClick(id) {
        //取消右键
        $('.layui-tab-title li').on('contextmenu', function () {
            return false;
        })
        //点击左键隐藏rigthMenu
        $('.layui-tab-title,.layui-tab-title li').on('click', function () {
            $('.rightMenu').hide();
        });
        //桌面点击右击
        $('.layui-tab-title li').on('contextmenu', function (e) {
            var aid = $(this).attr("lay-id"); //获取右键时li的lay-id属性
            var popupmenu = $(".rightMenu");
            //为所有右键菜单绑定data-id
            popupmenu.find("li").attr("data-id", aid);
            //console.log("popopmenuId:" + popupmenu.find("li").attr("data-id"));
            l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
            t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
            popupmenu.css({
                left: l,
                top: t
            }).show();
            //alert("右键菜单")
            return false;
        });

        $("#rightMenu li").click(function () {
            var type = $(this).attr("data-type");
            var layId = $(this).attr("data-id")
            //删除当前
            if (type == "current") {
                //console.log("close this:" + layId);
                tab.tabDelete(layId);
            } else if (type == "all") {
                //console.log("closeAll");删除所有
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
                tab.tabDeleteAll(ids);
            } else if (type == "fresh") {
                //console.log("fresh:" + layId);刷新
                tab.tabChange($(this).attr("data-id"));
                var othis = $('.layui-tab-title').find('>li[lay-id="' + layId + '"]'),
                    index = othis.parent().children('li').index(othis),
                    parents = othis.parents('.layui-tab').eq(0),
                    item = parents.children('.layui-tab-content').children('.layui-tab-item'),
                    src = item.eq(index).find('iframe').attr("src");
                item.eq(index).find('iframe').attr("src", src);
            } else if (type == "other") {
                var thisId = layId;
                $('.layui-tab-title').find('li').each(function (i, o) {
                    var layId = $(o).attr('lay-id');
                    if (layId != thisId && layId != 0) {
                        tab.tabDelete(layId);
                    }
                });
            }
            //再次隐藏右键
            $('.rightMenu').hide();
        })
    }

});