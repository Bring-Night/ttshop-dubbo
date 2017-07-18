<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2017/7/1 0001
  Time: 下午 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<ul id="contentCategory"></ul>
<%--右键菜单--%>
<div id="treeContextMenu" class="easyui-menu">
	<div onclick="append()" data-options="iconCls:'icon-add'">追加</div>
	<div onclick="edit()" data-options="iconCls:'icon-remove'">重命名</div>
	<div onclick="remove()" data-options="iconCls:'icon-remove'">移除</div>
</div>

<script>
    $(function () {
        $('#contentCategory').tree({
            url: 'content/category/list',
            onContextMenu: function (e, node) {
                e.preventDefault();
                // 查找节点
                //$('#tt').tree('select', node.target);
                // 显示快捷菜单
                $('#treeContextMenu').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });
            },
            onAfterEdit:function (node) {
                //更新节点操作
                var data = {id:node.id,name:node.text};
                $.post("content/category/update",data);
            }
        });
    });
    
    function append() {
        console.log("append");
        var currentNode = $("#contentCategory").tree('getSelected');
        var nodeData = {parentId: currentNode.id,name:'新分类'};
        $.post('content/category/create',nodeData,function (result) {
            if(result.success){
                $('#contentCategory').tree('append',{
                    parent:currentNode.target,
                    data:[{
                        id:result.data.id,
                        text:result.data.name
                    }]
                });
            }
        });
    }
    
    function edit() {
        console.log("edit");
        var currentNode = $("#contentCategory").tree('getSelected');
        $('#contentCategory').tree('beginEdit',currentNode.target);
    }
    
    function remove() {
        console.log("remove");
        $.messager.confirm('确认',"是否要删除当前类别",function (r) {
            if (r){
                var currentNode = $("#contentCategory").tree('getSelected');
                console.log(currentNode+'+++++++++++++'+currentNode.id)
                uri="content/category/del?id="+currentNode.id;
                //执行删除方法
                $.get(uri,function (result) {
                    //返回参数处理,封装返回结果扩展类
                    if (result.success&result.data>0){
                        $.messager.alert('提示',result.message,'info');

                        $('#contentCategory').tree('remove',currentNode.target);//重新载入树
                    }else if (!result.success){
                        $.messager.alert('警告',result.message,'warning');
                    }
                });
            }
        });
    }


</script>