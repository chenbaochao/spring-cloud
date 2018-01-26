var ue;

/**
 * 相册图片数组,包括图片ID和图片排序
 */
var productImages;
/**
 * 封面图片
 */
var thumbImage;

$(function(){
    thumbImage = product.thumb;
    productImages = product.pics;
    //初始化编辑器
    initUeditor();
    //初始化封面上传组件
    initThumbUpload();
    //初始化相册上传组件
    initGalleryUpload();
})

/**
 * 按钮点击事件
 */
$(function () {
    $('#submit').click(function(){
        if(!checkData()) return ;
        var data = bulidData();
        $.ajax({
            url:'../product/update',
            method:'post',
            dataType:"json",
            contentType:'application/json',
            data:JSON.stringify(data),
            success:function(response){
                if(response.status === 'SUCCESS'){
                    layer.msg("修改成功!", {
                        shade : 0.3,
                        icon : 1,
                        time : 1500
                    }, function() {
                        window.location.reload(); // 刷新
                    });
                }else{
                    layer.msg(response.errMsg, {
                        icon : 2,
                        time : 2000
                    });
                }
            },
            error:function () {
                layer.msg('操作失败', {
                    icon : 2,
                    time : 2000
                });
            }
        });


    });

});

/**
 * 构建数据
 */

function bulidData(){
    var data = {};
    data.id = $('#id').val();
    data.name = $('#name').val();
    data.showPrice = $('#showPrice').val();
    data.title = $('#title').val();
    data.thumb = thumbImage;
    data.introduction = ue.getContent();
    data.status = $('[name="status"]:checked').val();
    data.pics = productImages;
    var category = {};
    category.id = $('#category').val();
    data.category = category;
    var parameters = [];
    $('[name="paraName"]').each(function (i) {
        var para = {};
        para.id = $('[name="paraId"]')[i].value;
        para.name = $('[name="paraName"]')[i].value;
        para.value = $('[name="paraValue"]')[i].value;
        parameters.push(para);
    });
    data.parameters = parameters;
    var specs = [];
    $('[name="specName"]').each(function (i) {
        var spec = {};
        spec.id = $('[name="specId"]')[i].value;
        spec.name = $('[name="specName"]')[i].value;
        spec.price = $('[name="specPrice"]')[i].value;
        spec.stock = $('[name="specStock"]')[i].value;
        specs.push(spec);
    });
    data.specs = specs;
    //标签
    var labels = [];
    $('[name="labelId"]').each(function (i) {
        var label = {};
        label.id = $('[name="labelId"]')[i].value;
        label.name = $('[name="labelName"]')[i].value;
        labels.push(label);
    });
    data.labels = labels;
    data.remarks =  $('#remarks').val();
    return data;
}

/**
 * 数据校验
 */
function  checkData() {
    var errMsg = null;
    if(thumbImage.id === null) errMsg = '请上传封面';
    if(productImages.length < 1) errMsg = '请上传相册';
    if( $('#name').val() === null ||  $('#name').val().length < 2) errMsg = '产品名称长度需大于等于2';
    if($('#showPrice').val() <= 0) errMsg = '显示价格需大于0';
    if($('[name="specName"]').length === 0) errMsg = '请至少添加一种产品规格';
    if(errMsg !== null){
        layer.msg(errMsg, {
            shade : 0.3,
            time : 1500
        });
        return false;
    }
    return true;

}
/**
 * 初始化相册上传组件
 */
function initGalleryUpload(){
    //构造初始化数据
    var initialPreviewData = [];
    var initialPreviewConfigData = [];
    var pics = product.pics;
    for(var i = 0;i < pics.length; i++){
        initialPreviewData.push(pics[i].image.url);
        //这里没有给删除的url,所以图片并没有被真正删除,需要在后台处理
        var item = {};
        item.url = '../deleteSuccess';
        item.key = pics[i].image.id;
        initialPreviewConfigData.push(item);
    }
    //相册
    $("#galleryInput").fileinput({
        theme: 'fa',
        showClose:false,
        maxFileSize: 1999,
        maxFilesNum: 10,
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        overwriteInitial: false,
        uploadUrl:"../uploadImage",
        fileActionSettings:{
            //showDrag:false //设置不能拖动
        },
        initialPreviewAsData: true,
        initialPreview:initialPreviewData,
        initialPreviewConfig:initialPreviewConfigData
    }).on('fileuploaded', function(event, data, previewId, index) {
        var response = data.response;
        var initialPreviewConfig = response.initialPreviewConfig;
        //上传完后,保存信息
        var productImage = {};
        var image = {};
        image.id = initialPreviewConfig[0].key;
        productImage.sort = productImages.length;
        productImage.image = image;
        productImages.push(productImage);
    }).on('filedeleted', function(jqXHR, key, response) {
        //删除图片数组中指定的信息
        for(var i = 0; i < productImages.length;i++){
            if(productImages[i].image.id === key){
                productImages.splice(i, 1);
                break;
            }
        }
    });
}
/**
 * 初始化封面上传组件
 */
function initThumbUpload(){
    //封面
    $("#thumb").fileinput({
        theme: "gly",
        overwriteInitial: true,
        uploadUrl:"../uploadImage",
        maxFileSize: 99999,
        showClose: false,
        showCaption: false,
        browseLabel: '',
        removeLabel: '',
        showRemove:false,
        browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
        removeTitle: 'Cancel or reset changes',
        elErrorContainer: '#kv-avatar-errors-1',
        msgErrorClass: 'alert alert-block alert-danger',
        defaultPreviewContent: "<div style='width:230px;height:240px'></div>",
        allowedFileExtensions: ["jpg", "png", "gif"],
        browseOnZoneClick:false,
        fileActionSettings:{
            showDrag:false //设置不能拖动(拖动有BUG)
        },
        initialPreviewAsData: true,
        initialPreview:[
            product.thumb.url
        ],
        initialPreviewConfig:[
            {
                caption:'当前封面',
                key:product.thumb.id
            }
        ]
    }).on('fileuploaded', function(event, data, previewId, index) {
        var response = data.response;
        var initialPreviewConfig = response.initialPreviewConfig;
        thumbImage.id = initialPreviewConfig[0].key;
    });
}
/**
 * 初始化编辑器
 */
function initUeditor(){
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action === 'uploadImage') {
            return '/ueditor/uploadImage';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    }
    ue = UE.getEditor('editor',{
        toolbars:[
            [
                'anchor', //锚点
                'undo', //撤销
                'redo', //重做
                'bold', //加粗
                'indent', //首行缩进
                'snapscreen', //截图
                'italic', //斜体
                'underline', //下划线
                'strikethrough', //删除线
                'subscript', //下标
                'fontborder', //字符边框
                'superscript', //上标
                'formatmatch', //格式刷
                'source', //源代码
                'blockquote', //引用
                'pasteplain', //纯文本粘贴模式
                'selectall', //全选
                'print', //打印
                'preview', //预览
                'horizontal', //分隔线
                'removeformat', //清除格式
                'time', //时间
                'date', //日期
                'unlink', //取消链接
                'inserttitle', //插入标题
                'cleardoc', //清空文档
                'insertparagraphbeforetable', //"表格前插入行"
                'insertcode', //代码语言
                'fontfamily', //字体
                'fontsize', //字号
                'paragraph', //段落格式
                'simpleupload', //单图上传
                'insertimage', //多图上传
                'edittable', //表格属性
                'edittd', //单元格属性
                'link', //超链接
                'emotion', //表情
                'spechars', //特殊字符
                'map', //Baidu地图
                'insertvideo', //视频
                'justifyleft', //居左对齐
                'justifyright', //居右对齐
                'justifycenter', //居中对齐
                'justifyjustify', //两端对齐
                'forecolor', //字体颜色
                'backcolor', //背景色
                'insertorderedlist', //有序列表
                'insertunorderedlist', //无序列表
                'fullscreen', //全屏
                'directionalityltr', //从左向右输入
                'directionalityrtl', //从右向左输入
                'rowspacingtop', //段前距
                'rowspacingbottom', //段后距
                'pagebreak', //分页
                'insertframe', //插入Iframe
                'imagenone', //默认
                'imageleft', //左浮动
                'imageright', //右浮动
                'attachment', //附件
                'imagecenter', //居中
                'wordimage', //图片转存
                'lineheight', //行间距
                'edittip ', //编辑提示
                'customstyle', //自定义标题
                'autotypeset', //自动排版
                'webapp', //百度应用
                'touppercase', //字母大写
                'tolowercase', //字母小写
                'background', //背景
                'template', //模板
                'scrawl', //涂鸦
                'music', //音乐
                'inserttable', //插入表格
                'drafts', // 从草稿箱加载
                'charts', // 图表
            ]
        ]
    });
    UE.getEditor('editor').ready(function() {
        //this是当前创建的编辑器实例
        this.setContent(product.introduction);
    })
}




function addPara(){
    $('.ibox-content.parameter').append(paraImpl);
}

function addSpec(){
    $('.ibox-content.specification').append(specImpl);
}

function del(e){
    var p1 = e.parentNode;
    e.parentNode.parentNode.removeChild(p1);
}
function delLabel(e){
    var p1 = e.parentNode.parentNode;
    p1.parentNode.removeChild(p1);
}
function addLabel(){
    $('.form-group.labels').append(labelImpl);
}
//参数-dom数据
var paraImpl = '<div class="form-group parameter">' +
    '<input type="hidden" value="0" name="paraId" />' +
    '<label class="col-sm-1 col-xs-offset-2 control-label">参数名：</label>' +
    '<div class="col-sm-2">' +
    '<input type="text" maxlength="18"  class="form-control" name="paraName" >' +
    '</div>' +
    '<label class="col-sm-1 col-xs-offset-1 control-label">参数值：</label>' +
    '<div class="col-sm-2">' +
    '<input type="text" maxlength="18"  class="form-control" name="paraValue" >' +
    '</div>' +
    '<a class="remove m-r-sm text-danger"  onclick="del(this)" title="删除">' +
    '<i class="glyphicon glyphicon-remove" style="margin-top:8px;"></i>' +
    '</a>' +
    '</div>';
//规格-dom数据
var specImpl='<div class="form-group specification">' +
    '<input type="hidden" name="specId" value="0" />'+
    '<label class="col-sm-1 col-xs-offset-2 control-label">规格名：</label>' +
    '<div class="col-sm-2">' +
    '<input type="text" maxlength="18"  class="form-control" name="specName" >' +
    '</div>' +
    '<label class="col-sm-1 col-xs-offset-1 control-label">价格：</label>' +
    '<div class="col-sm-1">' +
    '<input type="number"  class="form-control" name="specPrice" >' +
    '</div>' +
    '<label class="col-sm-1 col-xs-offset-1 control-label">库存：</label>' +
    '<div class="col-sm-1">' +
    '<input type="number"  class="form-control" name="specStock" >' +
    '</div>' +
    '<a class="remove m-r-sm text-danger"  onclick="del(this)" title="删除">' +
    '<i class="glyphicon glyphicon-remove" style="margin-top:8px;"></i>' +
    '</a>' +
    '</div>';
//标签dom
var labelImpl = '<div class="col-sm-2 labels">' +
    '<div class="col-sm-8">' +
    '<input type="hidden" name="labelId" value="0"/>'+
    '<input type="text" maxlength="10"  class="form-control" name="labelName" >' +
    '</div>' +
    '<div class="col-sm-4">' +
    '<a class="remove m-r-sm text-danger"  onclick="delLabel(this)" title="删除">' +
    '<i class="glyphicon glyphicon-remove" style="margin-top:8px;"></i>' +
    '</a>' +
    '</div>' +
    '</div>';