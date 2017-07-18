<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2017/6/30 0030
  Time: 下午 7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="toolbar">
    <%--表单查询--%>
    <div>
        <label>商品标题:</label>
        <input name="titile" id="title" class="easyui-textbox">
        <label>商品状态:</label>
        <select name="status" id="status" class="easyui-combobox">
                <option value="0">全部</option>
                <option value="1">正常</option>
                <option value="2">下架</option>

        </select>
        <%--默认提交功能,明确功能按钮用button,pinyin4J.jar--%>
        <button type="button" class="easyui-linkbutton" onclick="searchForm()">搜索</button>
    </div>
    <%--工具按钮--%>
        <div>
            <a href="#" onclick="addTab('新增商品','item-add')" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增商品</a>
            <a href="#" onclick="deleteItem()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">删除</a>
            <a href="#" onclick="upItem()" class="easyui-linkbutton" data-options="iconCls:'icon-up'">上架</a>
            <a href="#" onclick="downItem()" class="easyui-linkbutton" data-options="iconCls:'icon-down'">下架</a>
        </div>
</div>
<%--tab页,打开就关闭第二个--%>
<table id="table"></table>

<script>
   

    //列表
    $('#table').datagrid({
        url: 'item/list-page',
        fit: true,
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        rownumbers: true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'ck', checkbox: 'true'},
            {field: 'id', title: '商品ID',sortable:'true'},
            {field: 'title', title: '商品标题',sortable:'true'},
            {field: 'catName', title: '叶子类目',sortable:'true'},
            {field: 'price', title: '价格', align: 'right', formatter: function (value) {
                return (value / 100).toFixed(2);
            },sortable:'true'},
            {field: 'num', title: '库存数量'},
            {field: 'barcode', title: '条形码'},
            {field: 'statusName', title: '状态'},
            {field: 'created', title: '创建日期', formatter: function (value) {
                return moment(value).format('YYYY-MM-DD hh:mm:SS');
            },sortable:'true'},
            {field: 'updated', title: '更新日期', formatter: function (value) {
                return moment(value).format('YYYY-MM-DD hh:mm:SS');
            },sortable:'true'},
            {field: 'sellPoint', title: '卖点',sortable:'true'},
        ]],
        /**
         * 加载页面之前
         */
        onBeforeLoad:function (param) {
            console.log(param.sort);
            var map = new Map();
            map['id'] = 'i.id';
            map['title'] = 'convert(i.title using gb2312)';
            map['price'] = 'i.price';
            map['created'] = 'i.created';
            map['updated'] = 'i.updated';
            map['sellPoint'] = 'convert(i.sell_point using gb2312)';
            map['catName'] = 'convert(c.name using gb2312)';


            //根据用户的选择获取自定义字段名
            var fileSort = map[param.sort];
            if (fileSort){
                //设置自定义字段名
                param.sort = fileSort;
            }
        }
    });

    /*条件查询*/
    function searchForm() {
        $('#table').datagrid('load',{
            title:$('#title').val(),
            status:$('#status').combobox('getValue')
        });
    }
</script>
