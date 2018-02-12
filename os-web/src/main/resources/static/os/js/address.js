$(function() {
	showLocation();
})

/**
 * 添加地址
 */
$(function() {
	$("#J_newAddress").on("click", function() {

		if ("object" != typeof $(this)) return !1;
		$("#J_modalEditAddress").show();
		var e = $(document).width(),
			f = $(document).height();
		$("#J_editAddrBackdrop").css({
			width : e,
			height : f
		}).show()
	})
})

/**
 * 关闭添加地址栏
 */
$(function() {
	$("#J_cancel").click(function() {
		$("#J_modalEditAddress").hide()
	})
})

/**
 * 保存收货地址
 */
$(function() {
	$("#J_save").click(function() {
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
        /////---------------------
	})
})

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
$('#J_editAddressSave').click(function(){
    $.ajax({
        type : "POST",
        url : baselocation + '/uc/user/address',
        data : a,
        dataType : "json",
        success : function(result) {
            if (result.code == 1) {
                window.location.reload();
            } else {
                layer.alert(result.message, {
                    icon : 2
                });
            }
        }
    })
});
function saveAddr(a) {
	if (a.addressId == null || a.addressId == "") {

	} else {
		$.ajax({
			type : "PUT",
			url : baselocation + '/uc/user/address/' + a.addressId,
			data : a,
			dataType : "json",
			success : function(result) {
				if (result.code == 1) {
					window.location.reload();
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	}
}

/**
 * 删除收获地址
 */
function address_delete(obj, data) {
	layer.confirm('确认要删除吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'delete',
			dataType : 'json',
			url : baselocation + '/uc/user/address/' + data,
			success : function(result) {
				if (result.code == 1) {
					$(obj).parent().parent("div").remove();
					layer.msg('已删除!', {
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

/**
*打开地址栏
*/
function Show(a) {
	if ("object" != typeof a) return !1;
	var b = a.offset().left - 15,
		c = a.offset().top,
		d = a.outerWidth() + 70;
	$(".address-edit-box").css({
		width : d,
		top : c,
		left : b
	}).show();
	var e = $(document).width(),
		f = $(document).height();
	$("#J_editAddrBackdrop").css({
		width : e,
		height : f
	}).show()
}

/**
*修改收货地址
*/
$(function() {
	$(".J_addressModify").on("click", function() {
		resetData();
		var loc = new Location();
		var b = $(this).parent().parent(),
			c = b.attr("data-consignee"),
			d = b.attr("data-province_id"),
			e = b.attr("data-province_name"),
			f = b.attr("data-city_id"),
			g = b.attr("data-city_name"),
			h = b.attr("data-district_id"),
			i = b.attr("data-district_name"),
			j = b.attr("data-address"),
			k = b.attr("data-zipcode"),
			l = b.attr("data-tel"),
			m = b.attr("data-tag_name"),
			n = b.attr("data-address_id");
		return $("#user_name").val(c),
			$("#user_adress").val(j),
			$("#user_tag").val(m),
			$("#user_zipcode").val(k),
			$("#user_phone").val(l),
			$("#address_id").val(n),
			$("#loc_province").find("option[value='" + d + "']").prop("selected", !0),
			$("#select2-chosen-1").html(e),
			loc.fillOption('loc_city', '0,' + d),
			$("#loc_city").find("option[value='" + f + "']").prop("selected", !0),
			$("#select2-chosen-2").html(g),
			loc.fillOption('loc_town', '0,' + d + ',' + f),
			$("#loc_town").find("option[value='" + h + "']").prop("selected", !0),
			$("#select2-chosen-3").html(i),
			Show(b), !1
	})
})

/**
 * 重置收货地址
 */
function resetData() {
	var loc = new Location();
	loc.fillOption('loc_province', '0');
	loc.fillOption('loc_city', '');
	loc.fillOption('loc_town', '');
	$("#user_name").val(""),
	$("#user_adress").val(""),
	$("#user_phone").val("").attr("placeholder", "11位手机号"),
	$("#user_tag").val(""),
	$("#user_zipcode").val(""),
	$("#address_id").html(""),
	$(".tipMsg").html("").hide();
}
