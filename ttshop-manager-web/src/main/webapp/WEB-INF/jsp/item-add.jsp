<%--
  Created by IntelliJ IDEA.
  User: zhy
  Date: 2017/6/30 0030
  Time: 下午 7:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="myform" id="myform" method="post">
    <table>
        <tr>
            <td class="label">商品类目:</td>
            <td>
                <select id="cid" name="cid" class="easyui-combotree" style="width: 200px"></select>
            </td>
        </tr>
        <tr>
            <td class="label">商品标题:</td>
            <td>
                <input class="easyui-textbox" id="title" name="title" style="width: 99%;"
                       data-options="required:true,validateOnCreate:false,missingMessage:'请输入商品标题'">
            </td>
        </tr>
        <tr>
            <td class="label">商品卖点:</td>
            <td>
                <input class="easyui-textbox" id="sellPoint" name="sellPoint" style="width: 99%"
                       data-options="multiline:true,validType:'length[0,200]',invalidMessage:'最多输入200个字'">
            </td>
        </tr>
        <tr>
            <td class="label">商品价格:</td>
            <td>
                <input class="easyui-numberbox" id="priceView" name="priceView"
                       data-options="required:true,validateOnCreate:false,missingMessage:'请输入商品价格',min:0,precision:2">
                <input type="hidden" name="price" id="price">


            </td>
        </tr>
        <tr>
            <td class="label">库存数量:</td>
            <td>
                <input class="easyui-textbox" id="num" name="num"
                       data-options="required:true,validateOnCreate:false,missingMessage:'请输入库存数量',min:0">
            </td>
        </tr>
        <tr>
            <td class="label">商品条形码:</td>
            <td>
                <input class="easyui-textbox" id="barcode" name="barcode"
                       data-options="required:true,validateOnCreate:false,invalidMessage:'最多输入30个字',validType:'length[0,30]'">
            </td>
        </tr>
        <tr>
            <td>标题图片</td>
            <td>
                <button type="button" id="upload_img_btn" class="easyui-linkbutton">图片上传</button>
                <ul id="upload_img_wrap"/>
                <%--加载编辑器内容--%>
                <script id="uploadImg"  type="text/plain" style="display: none;"/>
                    <input name="image" id="image" style="display:none;"></input>

            </td>
        </tr>
        <tr>
            <td colspan="2">
                <script id="container" name="desc" type="text/plain">
                </script>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="formBtn">
                <button class="easyui-linkbutton" type="button"
                        onclick="submitForm()"
                        data-options="iconCls:'icon-ok'">保存
                </button>
                <button class="easyui-linkbutton" type="button"
                        onclick="clearForm()"
                        data-options="iconCls:'icon-undo'">重置
                </button>
            </td>
        </tr>

    </table>

</form>
<script>

    $(function () {
        /*初始化类别选择下拉列表*/
        $('#cid').combotree({
            /* method:'get',*/
            url: 'item/cat/list',
            onBeforeSelect: function (node) {
                /*如果不是叶子节点不选中
                 * 是叶子返回true,不是返回false*/
                return $('#cid').tree('isLeaf', node.target);
            }
        });

        //销毁富文本编辑器
        UE.delEditor('container');
        //实例化编辑器
        var ue = UE.getEditor('container', {
            initialFrameWidth: '99%',
            initialFrameHeight: '200',
            serverUrl: 'file/upload'
        });

        //销毁富文本编辑器
        //实例化编辑器
        var uploadEditor = UE.getEditor('uploadImg', {
            initialFrameWidth: '99%',
            /* initialFrameHeight:'200',*/
            toolbars: [["insertimage"]],
            serverUrl: 'file/upload'
        });

        $('#upload_img_btn').click(function () {
            var dialog = uploadEditor.getDialog("insertimage");
            dialog.title = '图片上传';
            dialog.render();
            dialog.open();

        });

        uploadEditor.ready(function () {
            uploadEditor.addListener("beforeInsertImage", _beforeInsertImage);
        });

        function _beforeInsertImage(t,result) {
            console.log('_beforeInsertImage');
            console.log(t);
            console.log(result);
            //将图片差入到页面
            var imageHtml = '';
            var imgArr = [];
            for(var i in  result){
                console.log(i);
                imageHtml+='<li><img src="' + result[i].src + '"></li>';
                imgArr.push(result[i].src);
            }
            $('#upload_img_wrap').html(imageHtml);
            $('#image').val(imgArr.join(','));
        }
        //重置表单
        function clearForm() {
            $('myform').form('reset');
            ue.setContent("");
            uploadEditor.setContent('');

        }

    });



    /*提交表单*/
    function submitForm() {
        $('#myform').form('submit', {
            url: 'item/save',
            onSubmit: function () {
                //处理表单数据
                $('#price').val($('#priceView').val() * 100);
                //验证表单
                return $(this).form('validate');
            },
            success: function (result) {
                alert(result);
                result = JSON.parse(result);
                if (result.success) {
                    $.messager.alert('提示', result.message, 'info');
                    addTab('查询商品', 'item-list');
                }
            }
        });
        //$('.myform').submit();

    }

</script>