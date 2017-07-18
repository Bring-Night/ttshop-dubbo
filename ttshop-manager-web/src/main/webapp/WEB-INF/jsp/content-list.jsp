<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2017/7/1 0001
  Time: 下午 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="toolbar">
    <div>
        <button type="button" onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">
            新增
        </button>
        <button type="button" onclick="del()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true">
            删除
        </button>
    </div>
</div>
<div id="contentLayout" class="easyui-layout" data-options="fit:true">
    <div data-options="region:'west',split:false" style="width:150px; padding:5px">
        <ul id="contentCategory"></ul>
    </div>
    <div data-options="region:'center'" style="padding:5px">
        <table id="table"></table>
    </div>
</div>
<script>
    $(function () {

        var $datagrid = $("#table");
        var $tree = $("#contentCategory");

        $tree.tree({
            url: 'content/category/list',
            onClick: function (node) {
                var isLeaf = $tree.tree('isLeaf', node.target);
                if (isLeaf) {
                    //如果点击叶子节点，则加载内容数据
                    $datagrid.datagrid('reload', {
                        categoryId: node.id
                    });
                }
            }
        });

        //列表
        $datagrid.datagrid({
            title: '内容列表',
            url: 'content/query/list',
            fit: true,
            rownumbers: true,
            pagination: true,
            pageSize: 20,
            toolbar: '#toolbar',
            columns: [[
                {field: 'ck', checkbox: true},
                {field: 'id', title: 'ID'},
                {field: 'title', title: '内容标题'},
                {field: 'subTitle', title: '内容子标题'},
                {field: 'titleDesc', title: '内容描述'},
                {field: 'url', title: '内容连接', formatter: formatUrl},
                {field: 'pic', title: '图片', formatter: formatUrl},
                {field: 'pic2', title: '背景颜色'},
                {
                    field: 'created', title: '创建日期', formatter: function (value, row, index) {
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }
                },
                {
                    field: 'updated', title: '更新日期', formatter: function (value, row, index) {
                    return moment(value).format('YYYY年MM月DD日,hh:mm:ss');
                }
                }
            ]]

        });
    });
    function formatUrl(val, row) {
        if (val) {
            return "<a href='" + val + "' target='_blank'>查看</a>";
        }
        return "";
    }
    function add() {
        //新增前判断选择是否是叶子节点
        var node = $("#contentCategory").tree("getSelected");
        if (!node || !$("#contentCategory").tree("isLeaf", node.target)) {
            $.messager.alert('提示', '新增内容必须选择一个内容分类!', 'info');
            return;
        }

        //添加一个新的tab
        $('#tab').tabs('add', {
            title: '新增内容',
            href: 'content-add',
            closable: true
        });
    }
    function edit() {
        //TODO
    }
    function del() {
        doOperation('您确认想要删除记录么?', 'content/del?')
        /*
         ttshop.executeOperation('未选中数据!', '确定删除所选数据吗？', 'content/del?');
         */
    }
</script>
