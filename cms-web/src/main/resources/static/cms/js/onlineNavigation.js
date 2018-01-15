/**
 * 进行格式转换
 */
function timeFormatter(value) {
	return new Date(value).Format("yyyy-MM-dd HH:mm:ss");
}
function statusFormatter(value) {
	if (value) {
		return '<span class="label label-primary">显示</span>'
	} else{
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
			'<a class="log m-r-sm text-primary" href="javascript:void(0)" title="导航栏">',
			'<i class="glyphicon glyphicon-sort-by-attributes-alt"></i>',
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
			'<a class="log m-r-sm text-primary" href="javascript:void(0)" title="导航栏">',
			'<i class="glyphicon glyphicon-sort-by-attributes-alt"></i>',
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
		layer_show(row.name,  '/navigation/update?id=' + row.id, 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		admin_delete(index, row.id);
	},
	'click .log' : function(e, value, row, index) {
		window.location.href =  '/navigation/bar/list?navigationId=' + row.id ;
	}
};

/**
 * 隐藏导航
 */
function status_stop(index, value) {
	layer.confirm('确认要隐藏该导航吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'post',
			url :  '/navigation/hidden?id=' + value ,
			success : function(result) {
				if (result.status == 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 0,
						}
					});
					layer.msg('导航隐藏成功!', {
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
 * 显示导航
 */
function status_start(index, value) {
	layer.confirm('确认要显示该导航吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'post',
			url : '/navigation/show?id=' + value,
			success : function(result) {
				if (result.status == 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 1,
						}
					});
					layer.msg('导航显示成功!', {
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
 * 删除导航

function admin_delete(index, value) {
	layer.confirm('确认要删除该导航吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url :  '/online/navigation/' + value,
			success : function(result) {
				if (result.status == 'SUCCESS') {
					$('#table').bootstrapTable('hideRow', {
						index : index
					});
					layer.msg('该导航删除成功!', {
						icon : 1,
						time : 1000
					});
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}
 */
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
				message : '导航名称验证失败',
				validators : {
					notEmpty : {
						message : '导航名称不能为空'
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

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');
			
			var method = $('#form').attr('data-method');
			// Use Ajax to submit form data
			if (method == 'post') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
					url : $form.attr('action'),
					success : function(result) {
						if (result.status == 'SUCCESS') {
							parent.layer.msg("更新导航成功!", {
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
			}
			/*
			if (method == 'post') {
				$.ajax({
					data : $form.serialize(),
					dataType : 'json',
					type : 'post',
					url : $form.attr('action'),
					success : function(result) {
						if (result.code == 1) {
							parent.layer.msg("创建导航成功!", {
								shade : 0.3,
								time : 1500
							}, function() {
								window.parent.location.reload(); // 刷新父页面
							});
						} else {
							layer.msg(result.message, {
								icon : 2,
								time : 1000
							});
						}
					}
				})
			}*/
		});
})