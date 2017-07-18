/*通用添加TAB页*/
function addTab(title,href) {
    $("#tab").tabs('close',1);
    $("#tab").tabs('add',{
        title:title,
        href:href,
        closable:true
    });
}
/**
 * 商品逻辑删除方法
 */
function deleteItem() {
    doOperation('您确认想要删除记录么?','item/delete?')

}
/**
 * 上架商品
 */
function upItem() {
    doOperation('您确认想要上架商品么?','item/up?')

}
/**
 * 下架商品
 */
function downItem() {
    doOperation('您确认想要下架商品么?','item/down?')

}
/*逻辑删除,上架,下架,修改状态码*/
function doOperation(message,uri) {
    //获取所有被选中的行
    var rows=$("#table").datagrid('getSelections');
    //console.log(rows);
    //定义多选商品id数组
    var ids = [];
    //遍历赋值数组
    $.each(rows,function (index,rows) {
        var id = rows.id;
        ids.push("ids="+id);
    });
    if (ids.length ==0){
        $.messager.alert('提示','未选中商品','warning');
        return;
    }

    //拼接地址链接字符串
    var uri = uri+ids.join('&');
    //删除之前做提示,确认返回true,执行删除方法
    $.messager.confirm('确认',message,function (r) {
        if (r){
            //执行删除方法
            $.get(uri,function (result) {
                /*alert(result.success);
                alert(result.code);
                alert(result.data);*/
                //返回参数处理,封装返回结果扩展类
                if (result.success&result.data>0){
                    $.messager.alert('提示',result.message,'info');
                    $('#table').datagrid('reload');//重新载入当前页面
                }
            });
        }
    });
    
}
