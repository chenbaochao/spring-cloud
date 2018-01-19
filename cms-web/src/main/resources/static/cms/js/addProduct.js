var ue;

/**
 * 相册图片数组,包括图片ID和图片排序
 */
var productImages = [];
$(function(){
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
    var para = $(".form-group.parameter").clone();
    var spec = $(".form-group.specification").clone();


    ///相册
    $("#galleryInput").fileinput({
        theme: 'fa',
        showClose:false,
        maxFileSize: 1999,
        maxFilesNum: 10,
        allowedFileExtensions: ['jpg', 'png', 'gif'],
        overwriteInitial: false,
        uploadUrl:"../uploadImage",
        fileActionSettings:{
            //showDrag:false //设置不能拖动(拖动有BUG)
        },
        initialPreviewAsData: true,

    }).on('fileuploaded', function(event, data, previewId, index) {
        var response = data.response;
        var initialPreviewConfig = response.initialPreviewConfig;
        //上传完后,保存信息
        var productImage = {};
        productImage.id = initialPreviewConfig[0].key;
        productImage.sort = productImages.length;
        productImages.push(productImage);
        var item = "<span  name='productImage' data-id='"+initialPreviewConfig[0].key+"' data-sort='"+(productImages.length)+"'></span>";
        $("#galleryInput").after(item);
    }).on('filedeleted', function(jqXHR, key, response) {
        //删除图片数组中指定的信息
        for(var i = 0; i < productImages.length;i++){
            if(productImages[i].id === key){
                productImages.splice(i, 1);
                break;
            }
        }
    });

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
        }
    }).on('fileuploaded', function(event, data, previewId, index) {
        var response = data.response;
        console.log(response);
        $('#showPic').val(response.message.url);
    });
})




function addPara(){
    $('.ibox-content.parameter').append(para.clone());
}
function addSpec(){
    $('.ibox-content.specification').append(spec.clone());
}
function del(e){
    var p1 = e.parentNode;
    e.parentNode.parentNode.removeChild(p1);
}