/**
 * 地址选择
 */
var addressId = null;
$(function() {
	$(".J_addressItem").on("click", function() {
		$(this).addClass("selected").siblings().removeClass("selected");
		addressId = $(this).data("address_id");
/*		var html = '<div class="seleced-address" id="J_confirmAddress">' + c + '&nbsp;&nbsp;' + l + '<br>'
			+ e + '&nbsp;&nbsp;' + g + '&nbsp;&nbsp;' + i + '&nbsp;&nbsp;' + j + '&nbsp;&nbsp;';
		$(".section-bar").find(".fl:first-child").html(html);*/
	})
})


/**
 * 选择送货时间
 */
$(function() {
	$(".section-time .J_option").on("click", function() {
		$(this).addClass("selected").siblings().removeClass("selected");
	})
})

/**
 * 选择发票抬头
 */
$(function() {
	$(".section-invoice .J_option").on("click", function() {
		$(this).addClass("selected").siblings().removeClass("selected");
		var a = $(this).attr('data-value');
		if (a === "1") {
			$('.paper-invoice-company').addClass('hide')
			$('.tab-container .tab-content').addClass('hide')
		} else if (a === "2") {
			$('.paper-invoice-company').removeClass('hide')
			$('.tab-container .tab-content').addClass('hide')
		} else {
			$('.paper-invoice-company').removeClass('hide')
			$('.tab-container .tab-content').removeClass('hide')
		}
	})

	// 隐藏弹出框
	function e(obj) {
		return document.getElementById(obj)
	}
	e('J_showEinvoiceDetail').onclick = function(event) {
		$("#J_einvoiceDetail").removeClass('hide');
		stopBubble(event);
		document.onclick = function() {
			$("#J_einvoiceDetail").addClass('hide');
			document.onclick = null;
		}
	}

	e('J_einvoiceDetail').onclick = function(event) {
		//只阻止了向上冒泡，而没有阻止向下捕获，所以点击con的内部对象时，仍然可以执行这个函数
		stopBubble(event);
	}
	//阻止冒泡函数
	function stopBubble(e) {
		if (e && e.stopPropagation) {
			e.stopPropagation(); //w3c
		} else {
			window.event.cancelBubble = true; //IE
		}
	}
})

/**
 * 去结算
 */
$(function() {
	$("#J_checkoutToPay").on("click", function() {
		if (addressId === null) {
			layer.alert("请选择地址！", {
				icon : 2
			});
		}
		var cartId = [];
		$('[name="cartId"]').each(function(){
            cartId.push($(this).data('cart-id'));
        });
		if(cartId.length === 0){
            layer.alert("出错了,请刷新重试", {
                icon : 2
            });
        }

		$.ajax({
			url : '../order/order',
			type : 'post',
			dataType : 'json',
			data : {
			    cartId:cartId,
                addressId:addressId
            },
			success : function(result) {
				if (result.status === 'SUCCESS') {
					window.location.href = '/order/detail?id=' + result.message;
				} else {
					layer.alert(result.errMsg, {
						icon : 2
					});
				}
			}
		});
	})
})

/**
 * 导航分类栏显示及颜色变换
 */
$(function() {
	$('#J_navCategory').mouseover(function() {
		$('.site-category').css('display', 'block');
	})
	$('#J_navCategory').mouseout(function() {
		$('.site-category').css('display', 'none');
	})
});