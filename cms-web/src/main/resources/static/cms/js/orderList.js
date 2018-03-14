

function statusFormatter(value) {
	var span = '<span class="label label-primary">';
    switch(value){
		case 1:
			span+='代付款';break;
        case 2:
            span+='待发货';break;
        case 3:
            span+='已关闭';break;
        case 4:
            span+='待收货';break;
        case 5:
            span+='带评价';break;
        case 6:
            span+='已完成';break;
	}
	span+='</span>';
    return span;

}
//拼接产品名称
function nameFormatter(value, row, index) {
	var products = row.orderProducts;
	var span = '';
	for(var i = 0; i<products.length;i++){
		if(i !== 0)span+='，';
	    span+=products[i].productName;
	}
	return span;
}
function actionFormatter(value, row, index) {
    console.log(row.status);
	if (row.status === 2) {
		return [
			'<a class="updateStatus m-r-sm text-info" href="javascript:void(0)" title="设置为已发货">',
			'<i class="glyphicon glyphicon-plane"></i>',
			'</a>',
			'<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="查看详细信息">',
			'<i class="glyphicon glyphicon-eye-open"></i>',
			'</a>',
		].join('');
	} else {
		return [
			'<a class="detail m-r-sm text-warning" href="javascript:void(0)" title="查看详细信息">',
			'<i class="glyphicon glyphicon-eye-open"></i>',
			'</a>',
		].join('');
	}
}
function actionFormatter2(value, row, index) {
    if (row.status === 2) {
        return [
            '<a class="updateStatus m-r-sm text-info" href="javascript:void(0)" title="下架">',
            '<i class="glyphicon glyphicon-pause"></i>',
            '</a>',
        ].join('');
    } else {
        return [
            '<a class="updateStatus m-r-sm text-info" href="javascript:void(0)" title="上架">',
            '<i class="glyphicon glyphicon-play"></i>',
            '</a>',
            '<a class="edit m-r-sm text-warning" href="javascript:void(0)" title="编辑">',
            '<i class="glyphicon glyphicon-edit"></i>',
            '</a>',
        ].join('');
    }
}


window.actionEvents = {
	'click .updateStatus' : function(e, value, row, index) {
		setStatus(row.id);
	},
	'click .detail' : function(e, value, row, index) {
		alert('还没实现...');
        // layer_show(row.name, '../order/detail' + row.id, 900, 650)
	}
};

/**
 * 设置状态
 */
function setStatus(id) {
	layer.confirm('确认要设置为已发货状态？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			dataType : 'json',
			type : 'put',
			url : '/order/'+id+'/status/4',
			success : function(result) {
				if (result.status === 'SUCCESS') {
					$('#table').bootstrapTable('refresh', {
						silent : true
					});
					layer.msg('更新成功!', {
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



function queryParameters(parameter) {
    parameter.status = $('#status').val();
    return parameter;
}




/**
 *下拉列表改变后刷新表格,重新请求数据
 */

$('#status').change(function () {
    $('[name="refresh"]').click();
});
