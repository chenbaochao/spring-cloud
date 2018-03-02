$(function() {
	//showLocation();
})

/**
 * 添加地址
 */
$(function() {
	$("#J_newAddress").on("click", function() {
		resetData();
		if ("object" != typeof $(this)) return !1;
		$("#J_modalEditAddress").show();
		var e = $(document).width(),
			f = $(document).height();
		$("#J_editAddrBackdrop").css({
			width : e,
			height : f
		}).show()
	})
});

/**
 * 关闭添加地址栏
 */
$(function() {
	$("#J_cancel").click(function() {
		$("#J_modalEditAddress").hide()
	})
});

/**
 * 保存收货地址
 */
$(function() {
	$("#J_save").click(function() {
        $("#J_save").attr('disabled');
		var username = $("#username").val();
        if(username.length < 2 || username.length > 10 ){
            return $("#username").focus(), layer.msg('收件人名字长度错误(2-10之间))', {
                icon : 7
            });
        }
        var userPhone = $("#userPhone").val();
        if(userPhone.length !== 11 && userPhone.length !== 7 ){
            return $("#userPhone").focus(), layer.msg('电话号码长度只能为7或者11位', {
                icon : 7
            });
        }
        var userAddress = $("#userAddress").val();
        if(userAddress.length < 5 || userAddress.length > 100){
            return $("#userAddress").focus(), layer.msg('地址长度需大于5小于100', {
                icon : 7
            });
        }
		var  addressId = $('#addressId').val();
        if(addressId.toString() === '0'){
            addAddress();
        }else{
            updateAddress();
        }
        $("#J_save").removeAttr('disabled');
	});
});

/**
 * 验证长度
 */
function strLen(a) {
	return a.replace(/[^\x00-\xff]/g, "**").length
}

/**
 * 关闭地址栏
 */
function Close() {
	$("#J_modalEditAddress").hide()
}

/**
 * 保存收货地址
 */
function addAddress() {
    $.ajax({
        type : "POST",
        url :  '../address/add',
        data : {
            username:$("#username").val(),
            userAddress:$("#userAddress").val(),
            userPhone:$("#userPhone").val(),
            zipcode:$("#zipcode").val()
        },
        dataType : "json",
        success : function(result) {
            if (result.status === 'SUCCESS') {
                layer.msg("添加成功!", {
                    icon : 1,
                    shade : 0.3,
                    time : 1500
                }, function() {
                    window.location.reload(); // 刷新
                });
            } else {
                layer.alert(result.errMsg, {
                    icon : 2
                });
            }
        }
    })
}

/**
 * 修改地址
 */
function updateAddress() {
    $.ajax({
        type : "POST",
        url :  '/address/update',
        data : {
            username:$("#username").val(),
            userAddress:$("#userAddress").val(),
            userPhone:$("#userPhone").val(),
            zipcode:$("#zipcode").val(),
            id:$("#addressId").val()
        },
        dataType : "json",
        success : function(result) {
            if (result.status === 'SUCCESS') {
                layer.msg("修改成功!", {
                    icon : 1,
                    shade : 0.3,
                    time : 1500
                }, function() {
                    window.location.reload(); // 刷新
                });
            } else {
                layer.alert(result.errMsg, {
                    icon : 2
                });
            }
        }
    })
}


/**
 * 删除收货地址
 */
function del(obj) {
	layer.confirm('确认要删除吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		var id = $(obj).attr('data-id');
		$.ajax({
			type : 'post',
			dataType : 'json',
			url :  '/address/delete?id=' + id,
			success : function(result) {
				if (result.status === 'SUCCESS') {
                    layer.msg("删除成功!", {
                        icon : 1,
                        shade : 0.3,
                        time : 1500
                    }, function() {
                        $(obj).parent().parent("div").remove();
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
*修改收货地址
*/
$(function() {
	$(".J_addressModify").on("click", function() {
		resetData();
		var b = $(this).parent().parent();
		$("#username").val(b.attr('data-username'));
		$("#userAddress").val(b.attr('data-userAddress'));
		$("#zipcode").val(b.attr('data-zipcode'));
		$("#userPhone").val(b.attr('data-userPhone'));
		$("#addressId").val(b.attr('data-address-id'));
        $('#J_modalEditAddress').show();
	})
})

/**
 * 重置收货地址
 */
function resetData() {
	$("#username").val("");
	$("#userAddress").val("");
	$("#userPhone").val("");
	$("#zipcode").val("");
	$("#addressId").val(0);
	$(".tipMsg").html("").hide();
}
