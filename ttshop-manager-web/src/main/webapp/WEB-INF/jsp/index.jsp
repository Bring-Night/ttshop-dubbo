<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>天天商城</title>
    <link rel="stylesheet" href="css/web.css">
    <link rel="stylesheet" href="js/jquery-easyui-1.5/themes/icon.css">
    <link rel="stylesheet" href="js/jquery-easyui-1.5/themes/black/easyui.css">
</head>
<body>
<div id="cc" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north'" style="padding-left: 50px; height:100px;">
        <h1 style="text-align: center;">天天商城</h1>
    </div>
    <div data-options="region:'south'" style="padding-top: 8px; height:40px;">
        by zhy v1.0
    </div>
    <div data-options="region:'west',title:'导航',split:true" style="width:200px;">
        <div id="aa" class="easyui-accordion" >
            <div title="商品管理" style="padding:10px;"data-options="selected:true" >
                <ul class="easyui-tree nav-tree">
                    <li data-options="attributes:{'href':'item-add'}">新增商品</li>
                    <li data-options="attributes:{'href':'item-list'}">查询商品</li>
                    <li data-options="attributes:{'href':'item-param-list'}">参数规格</li>
                </ul>

            </div>
            <div title="广告管理"style="padding:10px;" >
                <ul class="easyui-tree nav-tree">
                    <li data-options="attributes:{'href':'content-category'}">分类管理</li>
                    <li data-options="attributes:{'href':'content-list'}">内容管理</li>
                </ul>
            </div>
            <div title="索引管理"style="padding:10px;">
                <ul class="easyui-tree nav-tree">
                    <li data-options="attributes:{'href':'index-item'}">索引库维护</li>
                </ul>
            </div>
        </div>
    </div>
    <div data-options="region:'center'">
        <div id="tab" class="easyui-tabs" data-options="fit:true">
            <div title="主页面"  style="padding:20px;">

            </div>
        </div>

    </div>
</div>


<script src="js/jquery-easyui-1.5/jquery.min.js"></script>
<script src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script src="js/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script src="js/moment/moment.min.js"></script>
<script src="js/moment/moment-with-locales.js"></script>
<script>
    moment.local('zh-cn');
</script>
<script src="js/ueditor1_4_3_3/ueditor.config.js"></script>
<script src="js/ueditor1_4_3_3/ueditor.all.min.js"></script>
<%--解决多图上传405--%>
<script type="text/javascript">
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage') {
            //return 'http://localhost:8080/ttshop/file/upload';
            return '${pageContext.request.contextPath}/file/upload';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>

<%--自定义JS--%>
<script src="js/common.js"></script>
<script>
    $('.nav-tree').tree({
        onClick:function (node) {
            console.log(node);
            addTab(node.text,node.attributes.href);
           /* $("#tab").tabs('close',1);
            $("#tab").tabs('add', {
                title: node.text,
                href: node.attributes.href,
                closable: true
            });*/
        }
    });
</script>
</body>
</html>