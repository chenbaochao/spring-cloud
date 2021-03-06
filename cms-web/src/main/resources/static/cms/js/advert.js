/**
 * 进行格式转换
 */
function statusFormatter(value) {
	if (value === 1) {
		return '<span class="label label-primary">显示</span>'
	} else if (value === 0) {
		return '<span class="label label-danger">隐藏</span>'
	}
}
function picImgFormatter(value, row) {
	return '<a href="' + value + '" target="_blank" title="' + row.title + '">' + value + '</a>';
}

function actionFormatter(value, row, index) {
	if (row.status === 1) {
		return [
			'<a class="freeze m-r-sm text-info" href="javascript:void(0)" title="隐藏">',
			'<i class="glyphicon glyphicon-pause"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>',
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
			'</a>',
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
		layer_show(row.title, '../advert/update?id=' + row.id , 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		admin_delete(index, row.id);
	},
};

/**
 * 隐藏广告详情
 */
function status_stop(index, value) {
	layer.confirm('确认要隐藏该广告详情吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
			url : '/advert/'+id+'/status/0',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 0
						}
					});
					layer.msg('该导航隐藏栏成功!', {
						icon : 5,
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
 * 显示广告详情
 */
function status_start(index, value) {
	layer.confirm('确认要显示该广告详情吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
            url : '/advert/'+id+'/status/1',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 1
						}
					});
					layer.msg('该导航显示栏成功!', {
						icon : 6,
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
 * 删除广告
 */
function admin_delete(index, value) {
	layer.confirm('确认要删除该广告详情吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url : '/advert/'+id,
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('hideRow', {
						index : index
					});
					layer.msg('该广告详情删除成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.errMsg, {
						icon : 2
					});
				}
			},
            error:function(){
                layer.alert('网络繁忙,请重试', {
                    icon : 2
                });
            }
		})
	});
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
 * 查看按钮
 */
$(function() {
	$('#view').on("click", function() {
		if ($('input[name="showPic"]').val() != null && $('input[name="showPic"]').val() != "") {
			window.open($('input[name="showPic"]').val());
		}
	})
})

/**
 * 图片上传
 */
$(function() {
	$('#upload').on("click", function() {
		var formData = new FormData();
		formData.append('upFile', $('input[type="file"]')[0].files[0]);
		$.ajax({
			url : '/ueditor/image',
			type : 'post',
			cache : false,
			data : formData,
			processData : false, //因为data值是FormData对象,不需要对数据做处理
			contentType : false, //因为是由<form>表单构造的FormData对象,且已经声明了属性enctype="multipart/form-data",所以这里设置为false
			success : function(result) {
				if (result.state === 'SUCCESS') {
					parent.layer.msg("图片上传成功!", {
						shade : 0.3,
						time : 1500
					});
					$('input[name="showPic"]').val(result.url);
					$('.view-button').show();
				} else {
					layer.msg('出错了', {
						icon : 2,
						time : 1000
					});
				}
			},
            error:function(){
                layer.alert('网络繁忙,请重试', {
                    icon : 2
                });
            }
		});
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
			'title' : {
				message : '广告标题验证失败',
				validators : {
					notEmpty : {
						message : '广告标题不能为空'
					}
				}
			},
			'href' : {
				message : '链接地址验证失败',
				validators : {
					notEmpty : {
						message : '链接地址不能为空'
					}
				}
			},
			'sort' : {
				message : '排序验证失败',
				validators : {
					notEmpty : {
						message : '排序不能为空'
					},
					regexp : {
						regexp : /^[0-9]*$/,
						message : '排序只能为数字'
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

			var action = $('#form').attr('data-action');
			// Use Ajax to submit form data
			if (action === 'update') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'put',
					url :'/advert',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("更新广告详情成功!", {
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
                        layer.alert('网络繁忙,请重试', {
                            icon : 2
                        });
                    }
				})
			} else {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
					url : '/advert',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("创建广告详情成功!", {
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
                        layer.alert('网络繁忙,请重试', {
                            icon : 2
                        });
                    }
				})
			}
		});
})