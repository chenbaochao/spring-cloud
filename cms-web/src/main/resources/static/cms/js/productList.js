

function statusFormatter(value) {
	if (value === 1) {
		return '<span class="label label-primary">已上架 </span>'
	} else if (value === 0) {
		return '<span class="label label-danger">已下架</span>'
	}
}

function actionFormatter(value, row, index) {
	if (row.status === 1) {
		return [
			'<a class="freeze m-r-sm text-info" href="javascript:void(0)" title="下架">',
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
			'<a class="normal m-r-sm text-info" href="javascript:void(0)" title="上架">',
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
		window.location.href='/product/update?id=' + row.id;
	},
	'click .remove' : function(e, value, row, index) {
		del(index, row.id);
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



function queryParameters(parameter) {
    parameter.categoryId = $('#category').val();
    return parameter;
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




/**
 *下拉列表改变后刷新表格,重新请求数据
 */

$('#category').change(function () {
    $('[name="refresh"]').click();
});
