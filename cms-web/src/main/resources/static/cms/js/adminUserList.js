/**
 * 进行格式转换
 */

function statusFormatter(value) {
	if (value === 1) {
		return '<span class="label label-primary">正常</span>'
	} else if (value === 0) {
		return '<span class="label label-danger">冻结</span>'
	}
}
function actionFormatter(value, row, index) {
	if (row.status === 1) {
		return [
			'<a class="freeze m-r-sm text-info" href="javascript:void(0)" title="冻结">',
			'<i class="glyphicon glyphicon-pause"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>',
			'<a class="log m-r-sm text-primary" href="javascript:void(0)" title="日志">',
			'<i class="glyphicon glyphicon-list-alt"></i>',
			'</a>'
		].join('');
	} else {
		return [
			'<a class="normal m-r-sm text-info" href="javascript:void(0)" title="启用">',
			'<i class="glyphicon glyphicon-play"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
			'<i class="glyphicon glyphicon-edit"></i>',
			'</a>',
			'<a class="remove m-r-sm text-danger" href="javascript:void(0)" title="删除">',
			'<i class="glyphicon glyphicon-remove"></i>',
			'</a>',
			'<a class="log m-r-sm text-primary" href="javascript:void(0)" title="日志">',
			'<i class="glyphicon glyphicon-list-alt"></i>',
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
		layer_show(row.userName,  '/administrator/update?id=' + row.id , 900, 650)
	},
	'click .remove' : function(e, value, row, index) {
		admin_delete(index, row.id);
	},
	'click .log' : function(e, value, row, index) {
		layer_show(row.username + '登录日志',  '/administrator/loginLog?adminId='+row.id, 900, 650)
	}
};

/**
 * 冻结管理员
 */
function status_stop(index, value) {
	layer.confirm('确认要冻结该管理员吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
			url :  '/administrator/'+value+'status/0',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 0
						}
					});
					layer.msg('该管理员冻结成功!', {
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
 * 启用管理员
 */
function status_start(index, value) {
	layer.confirm('确认要启用该管理员吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
            url :  '/administrator/'+value+'status/1',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('updateRow', {
						index : index,
						row : {
							status : 1
						}
					});
					layer.msg('该管理员启用成功!', {
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
 * 删除管理员
 */
function admin_delete(index, value) {
	layer.confirm('确认要删除该管理员吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url : '/administrator/'+value,
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('hideRow', {
						index : index
					});
					layer.msg('删除成功!', {
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