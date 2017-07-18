<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2017/7/1 0001
  Time: 下午 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form class="myform" id="myform" method="post">
	<input type="hidden" id="categoryId" name="categoryId" />
	<table style="width: 100%">

		<tr>
			<td class="label">内容标题:</td>
			<td><span id="showCurrentName"></span></td>
		</tr>

		<tr>
			<td class="label">内容标题:</td>
			<td><input class="easyui-textbox" type="text" name="title"
				data-options="required:true" style="width: 100%;"></td>
		</tr>

		<tr>
			<td class="label">内容子标题:</td>
			<td><input class="easyui-textbox" type="text" name="subTitle"
				style="width: 100%;"></td>
		</tr>
		<tr>
			<td class="label">内容描述:</td>
			<td><input class="easyui-textbox" name="titleDesc"
				data-options="multiline:true,validType:'length[0,150]'"
				style="height: 60px; width: 100%;"></input></td>
		</tr>
		<tr>
			<td class="label">URL:</td>
			<td><input class="easyui-textbox" type="text" name="url"
				style="width: 100%;"></td>
		</tr>

		<tr>
			<td class="label">标题图片</td>
			<td>
				<button type="button" id="upload_img_btn" class="easyui-linkbutton">图片上传</button>
				<ul id="upload_img_wrap">
					<!-- 显示上传后的图片 -->
				</ul> <!-- 加载编辑器的容器 --> <script id="uploadImg" type="text/plain"
					style="display: none;" /> <input name="pic" id="pic"
				style="display: none;">
			</td>
		</tr>

		<tr>
			<td colspan="2"><script id="container" name="content"
					type="text/plain" /></td>
		</tr>

		<tr>
			<td colspan="2">
				<button class="easyui-linkbutton" type="button"
					data-options="iconCls:'icon-ok'" onclick="submitForm()">保存
				</button>
				<button class="easyui-linkbutton" type="button"
					data-options="iconCls:'icon-undo'" onclick="clearForm()">重置
				</button>
			</td>
		</tr>
	</table>
</form>

<script>
    var ue;
    var uploadEditor;

    $(function () {

        //先做一个编辑器的销毁(解决第二次无法加载的问题)
        UE.delEditor('container');
        //实例化(富文本)编辑器
        ue = UE.getEditor('container', {
            initialFrameWidth: '100%',
            initialFrameHeight: '500',
            serverUrl: 'file/upload'
        });

        //点击“图片上传”按钮，打开图片上传对话框
        $('#upload_img_btn').click(function () {
            var dialog = uploadEditor.getDialog("insertimage");
            dialog.title = '图片上传';
            dialog.render();
            dialog.open();
        });

        //图片上传按钮
        //实例化编辑器
        uploadEditor = UE.getEditor('uploadImg', {
            initialFrameWidth: '100%',
            toolbars: [['insertimage']],
            serverUrl: 'file/upload'
        });

        uploadEditor.ready(function () {
            //console.log('I am ready');
            uploadEditor.addListener('beforeInsertImage', _beforeInsertImage);
        });

        //初始化分类id
        var currentNode = $("#contentCategory").tree("getSelected");
        $('#categoryId').val(currentNode.id);
        $('#showCurrentName').html(currentNode.text);

    });

    function _beforeInsertImage(t, result) {
        console.log('_beforeInsertImage');
        //将刚上传的图片插入到页面中

        var imageHtml = '';
        var imgArr = [];
        for (var i in result) {
            imageHtml += '<li><img src = "' + result[i].src + '" ></li>';
            imgArr.push(result[i].src);
        }
        $('#upload_img_wrap').html(imageHtml);
        $('#pic').val(imgArr.join(','));
    }

    //表单提交
    function submitForm() {

        $('#myform').form('submit', {
            url: 'content/save',
            onSubmit: function () {
                // do some check
                // return false to prevent submit;
                //验证表单
                return $(this).form('validate');
            },
            success: function (result) {
                result = JSON.parse(result);
                if (result.success) {
                    $.messager.alert('提示', result.message, 'info');
                    //关闭当前tab
                    $('#tab').tabs('close', '新增内容');
                    //刷新内容列表
                    $("#table").datagrid("reload");
                }
            }
        });
    }

    //重置表单
    function clearForm() {
        $('#contentAddForm').form('reset');
        ue.setContent('商品描述');
    }
</script>