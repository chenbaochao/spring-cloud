/**
 * 进行格式转换
 */
function statusFormatter(value) {
	if (value) {
		return '<span class="label label-primary">显示</span>'
	} else {
		return '<span class="label label-danger">隐藏</span>'
	}
}
function actionFormatter(value, row, index) {
	if (row.status) {
		return [
			'<a class="freeze m-r-sm text-info" href="javascript:void(0)" title="隐藏">',
			'<i class="glyphicon glyphicon-pause"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>'
		].join('');
	} else {
		return [
			'<a class="normal m-r-sm text-info" href="javascript:void(0)" title="显示">',
			'<i class="glyphicon glyphicon-play"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>'
		].join('');
	}
}

window.actionEvents = {
	'click .freeze' : function(e, value, row, index) {
		status_stop(index, row.id);
	},
	'click .normal' : function(e, value, row, index) {
		status_start(index, row.id);
	},
	'click .edit' : function(e, value, row, index) {
		layer_show(row.name, '../section/update?id=' + row.id , 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		admin_delete(index, row.id);
	},
};

/**
 * 隐藏栏目
 */
function status_stop(index, value) {
	layer.confirm('确认要隐藏该栏目吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'post',
			data:{
				id:value
			},
			url : '../section/setInvalid',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 0,
						}
					});
					layer.msg('该栏目隐藏成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.errMsg, {
						icon : 2
					});
				}
			}
		})
	});
}

/**
 * 显示栏目
 */
function status_start(index, value) {
	layer.confirm('确认要显示该栏目吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
            dataType : 'json',
            type : 'post',
            data:{
                id:value
            },
            url : '../section/setValid',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 1,
						}
					});
					layer.msg('该栏目显示栏成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.errMsg, {
						icon : 2
					});
				}
			}
		})
	});
}

/**
 * 删除栏目
 */
function admin_delete(index, value) {
	layer.confirm('确认要删除该栏目吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
            dataType : 'json',
            type : 'post',
            data:{
                id:value
            },
            url : '../section/delete',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('hideRow', {
						index : index
					});
					layer.msg('该栏目删除成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.errMsg, {
						icon : 2
					});
				}
			}
		})
	});
}

/**
 * 初始化菜单树
 */
var ztreeObject;
var setting = {
    data : {
        simpleData : {
            enable : true,
            idKey : "id",
            pIdKey : "parentId",
            rootPId : 0
        },
        key : {
            name : 'name',
            title : 'name'
        }
    },
    check : {
        enable : true,
        nocheckInherit : true
    }
};
var treeData = [];
$(function() {
    if(categoriesJson === '') return;
    var categories = eval('(' + categoriesJson + ')');

    for(var i  in categories){
        var parentNode = {};
        parentNode.id = categories[i].id;
        parentNode.name =  categories[i].name;
        treeData.push(parentNode);
    }
    ztreeObject = $.fn.zTree.init($("#ztree"), setting, treeData);
    //展开所有节点
    ztreeObject.expandAll(true);
    //更新勾选操作
    try{
        if(sectionCategoriesJson !== undefined){
            initTreeData();
        }
    }catch(e){

    }
});

/**
 * 选中数据
 */
function initTreeData(){
    var sectionCategories = eval('(' + sectionCategoriesJson + ')');
    for(var i = 0; i < sectionCategories.length; i++){
        var node = ztreeObject.getNodeByParam("id", sectionCategories[i].id, null);
        ztreeObject.checkNode(node, true, true);

    }
}

/**
 * 构建json数据并返回
 */
function bulidData(){
    var section = {};
    section.id = $('#id').val();
    section.name = $('#name').val();
    section.sort = $('#sort').val();
    section.status = $('[name="status"]:checked').val();
    ztreeObject = $.fn.zTree.getZTreeObj("ztree");
    var nodes = ztreeObject.getCheckedNodes(true);
    var categories = [];
    if (nodes !== null && nodes.length > 0) {
        for (var i = 0; i < nodes.length; i++) {
            var category = {};
            category.id = nodes[i].id;
            categories.push(category);
        }
    }
    section.categories = categories;
    return section;
}
/**
 * 多选框插件
 */
$(document).ready(function() {
	$('input').iCheck({
		checkboxClass : 'icheckbox_flat-green',
		radioClass : 'iradio_flat-green'
	});
});

/**
 * 系统提示
 */
$(function() {
	$('.status-tip').on("click", function() {
		layer.tips('"显示" 代表此数据可用<br>"隐藏" 代表此数据不可用', '.status-tip');
	})
})


/**
 * 表单验证
 */
$(function() {
	$('#form').bootstrapValidator({
		container : 'tooltip',
		message : 'This value is not valid',
		feedbackIcons : {
			valid : 'glyphicon glyphicon-ok',
			invalid : 'glyphicon glyphicon-remove',
			validating : 'glyphicon glyphicon-refresh'
		},
		fields : {
			'name' : {
				message : '导航栏名称验证失败',
				validators : {
					notEmpty : {
						message : '导航名称栏不能为空'
					}
				}
			},
			'sort' : {
				message : '排序验证失败',
				validators : {
					notEmpty : {
						message : '排序不能为空'
					},
		            regexp: {
		                regexp: /^[0-9]*$/,
		                message: '排序只能为数字'
		            }
				}
			},				
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);
            var data = bulidData();
			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');
			
			var action = $form.data('action');
			if(action === "create"){
			    var sectionCategory = {};
			    sectionCategory.id = $('#sectionCategoryId').val();
			    data.sectionCategory = sectionCategory;
            }
			// Use Ajax to submit form data
			if (action === 'update') {
				$.ajax({
                    contentType:'application/json',
                    data:JSON.stringify(data),
					dataType : 'json',
					type : 'post',
					url : '../section/update',
					success : function(result) {
						if (result.status ==='SUCCESS') {
							parent.layer.msg("更新导航栏成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.errMsg, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			} else {
				$.ajax({
					dataType : 'json',
					type : 'post',
                    contentType:'application/json',
                    data:JSON.stringify(data),
                    url : '../section/create',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("创建导航栏成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.errMsg, {
								icon : 2,
								time : 1000
							});
						}
					},
                    error:function(){
                        layer.msg('网络繁忙', {
                            icon : 2,
                            time : 1000
                        });
                    }
				})
			}
			$('#action').removeAttr('disabled')
		});
})