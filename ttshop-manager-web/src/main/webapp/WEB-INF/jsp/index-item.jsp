<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2017/7/1 0001
  Time: 下午 2:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<button id="import" class="easyui-linkbutton" onclick="importItems()">一键导入商品到搜索引擎</button>
<div id="msg"></div>
<script>
function importItems(){
	$('#import').linkbutton('disable');
	$('#msg').html('导入中请耐心等待......');
	$.post('search/item/import',function(result){
		if(result.success){
			$.messager.alert('提示',result.message,'info');
		}else{
			$.messager.alert('错误',result.message,'error');
		}
		$('#import').linkbutton('enable');
		$('#msg').html('');
	});
}
</script>