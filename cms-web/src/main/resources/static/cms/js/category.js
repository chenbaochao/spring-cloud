
function statusFormatter(value) {
	if (value === 1) {
		return '<span class="label label-primary">显示 </span>'
	} else if (value === 0) {
		return '<span class="label label-danger">隐藏</span>'
	}
}

function topFormatter(value) {
	if (value === 1) {
		return '<span class="label label-primary">是</span>'
	} else if (value === 0) {
		return '<span class="label label-danger">否</span>'
	}
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
		setStatus(row.id, 0);
	},
	'click .normal' : function(e, value, row, index) {
        setStatus(row.id,1);
	},
	'click .edit' : function(e, value, row, index) {
		layer_show(row.name,  '/product/category/update?id=' + row.id , 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		del(index, row.id);
	},
	'click .log' : function(e, value, row, index) {
		layer_show(row.name, '/product/category/create', 900, 650)
	}
};

/**
 * 设置状态
 */
function setStatus(id, status) {
	layer.confirm(status===1?'确认要显示该分类吗？':'确认要隐藏该分类吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
			url : '/product/category/'+id+'/status/'+status,
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('refresh', {
						silent : true
					});
					layer.msg('更改成功!', {
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
 * 删除分类
 */
function del(index, id) {
	layer.confirm('确认要删除该分类吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url : '/product/category/'+id,
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('hideRow', {
						index : index
					});
					layer.msg('该分类删除成功!', {
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



var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

elems.forEach(function(html) {
	var switchery = new Switchery(html, {
		size : 'small'
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
				message : '分类名称验证失败',
				validators : {
					notEmpty : {
						message : '分类名称不能为空'
					}
				}
			}
		}
	})
		.on('success.form.bv', function(e) {
			// Prevent form submission
			e.preventDefault();

			// Get the form instance
			var $form = $(e.target);

			// Get the BootstrapValidator instance
			var bv = $form.data('bootstrapValidator');
            var data = $form.serialize();
            if(data.indexOf('status') === -1){
                data+='&status=0';
            }
            if(data.indexOf('showInTop') === -1){
                data+='&showInTop=0';
            }
			var action = $('#form').attr('data-action');
			// Use Ajax to submit form data
			if (action === 'update') {
				$.ajax({
					data : data,
					dataType : 'json',
					type : 'put',
					url : '/product/category',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("更新分类成功!", {
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
			} else if (action === 'create') {
				$.ajax({
					data : data,
					dataType : 'json',
					type : 'post',
					url : '/product/category',
					success : function(result) {
						if (result.status === 'SUCCESS') {
							parent.layer.msg("创建分类成功!", {
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
		});
})