$(function() {
	//question_help(); // 默认显示最有帮助商品提问

    showComment();
    show_header();
    siteHeaderInit();
});


/**
* 图片介绍动画切换效果
*/
$(function() {
	$("#goodsPicList").on('click', 'li', function() {
		$("#goodsPicList li").removeClass("current");
		$(this).addClass("current");
		var largePath = $(this).children("img").attr("src");
		$("#J_BigPic").attr({
			src : largePath
		})
	});
	$("#goodsPicList li:first").click(); //第一张图片
})

/**
 * 导航悬浮
 */
$(window).scroll(function() {
	var $this = $(this);
	var targetTop = $(this).scrollTop();
	var height = $(window).height();

	//document.title=targetTop;
	//控制导航悬浮
	if (targetTop > 800) {
		$("#goodsSubBar").css('display', 'block');
	} else {
		$("#goodsSubBar").css('display', 'none');
	}

	if (targetTop > $("#goodsDesc").offset().top - 100 && targetTop < $("#goodsParam").offset().top - 100) {
		$(".goods-sub-bar .detail-list").find("li").removeClass("current");
		$(".goods-sub-bar .detail-list").find("li").eq(0).addClass("current");
	} else if (targetTop > $("#goodsParam").offset().top - 100 && targetTop < $("#goodsComment").offset().top - 100) {
		$(".goods-sub-bar .detail-list").find("li").removeClass("current");
		$(".goods-sub-bar .detail-list").find("li").eq(1).addClass("current");
	} else if (targetTop > $("#goodsComment").offset().top - 100 && targetTop < $("#goodsFaq").offset().top - 100) {
		$(".goods-sub-bar .detail-list").find("li").removeClass("current");
		$(".goods-sub-bar .detail-list").find("li").eq(2).addClass("current");
	} else if (targetTop > $("#goodsFaq").offset().top - 100) {
		$(".goods-sub-bar .detail-list").find("li").removeClass("current");
		$(".goods-sub-bar .detail-list").find("li").eq(3).addClass("current");
	}
});


/**
 * 导航悬浮点击事件
 */
var subNav_active = $(".current");
var subNav_scroll = function(target) {
	subNav_active.removeClass("current");
	target.parent().addClass("current");
	subNav_active = target.parent();
};
$(".goods-sub-bar .detail-list a").click(function() {
	subNav_scroll($(this));
	if ($(this).parent().attr("id") != "join") {
		var target = $(this).attr("href");
		var targetScroll = $(target).offset().top - 40;
		$("html,body").animate({
			scrollTop : targetScroll
		}, 300);
		return false;
	}
});
$(".goods-detail-nav .detail-list a").click(function() {
	subNav_scroll($(this));
	if ($(this).parent().attr("id") != "join") {
		var target = $(this).attr("href");
		var targetScroll = $(target).offset().top - 40;
		$("html,body").animate({
			scrollTop : targetScroll
		}, 300);
		return false;
	}
});
$(".goods-info-head-userfaq .detail-list a").click(function() {
	if ($(this).parent().attr("id") != "join") {
		var target = $(this).attr("href");
		var targetScroll = $(target).offset().top - 40;
		$("html,body").animate({
			scrollTop : targetScroll
		}, 300);
		return false;
	}
});

/**
 * 商品规格选择
 */
var productSpecId;
$(function() {
	$(".goods-info-head .sys_item_specpara").each(function() {
		var i = $(this);
		var p = i.find("ul>li");
		p.click(function() {
			$(this).addClass("current").siblings("li").removeClass("current");
			i.attr("data-attrval", $(this).attr("data-aid"))
			var price = $(this).data('price');
            productSpecId = $(this).data('spec-id');
            $('#show_price').text(price);
		})
	})

	var $elements = $('.sys_item_specpara');
	$elements.each(function() {
		var $this = $(this);
		$this.children('ul').children('li:first').click(); //第一种规格
	})
})

/**
 * 默认商品规格
 */
$(function() {
	if ($('#J_goodsInfoBlock').children('.sys_item_specpara').length <= 0) {
		// 默认商品规格
		_score = sys_item['default']['score'];
		_price = sys_item['default']['price'];
		_productSpecNumber = sys_item['default']['productSpecNumber'];

		// 输出对应的class
		var _resp = {
			score : ".sys_item_score",
			price : ".sys_item_price",
		}

		$(_resp.score).text(_score); // 其中的math.round为截取小数点位数
		$(_resp.price).text(_price);
		$("#goodsDetailAddCartBtn").attr("data-productapi-spec-number", _productSpecNumber);
		$("#goodsSubBarAddCartBtn").attr("data-productapi-spec-number", _productSpecNumber);
	}
})



/**
 * 评论
 */
function showComment() {
	$.ajax({
		type : 'get',
		dataType : 'text',
		url :  '../comment/itemList?productId=' + $('#id').val(),
		success : function(result) {
			$("#J_supComment").html(result);
		}
	});

}

/**
 * 加入购物车
 */
function add_cart(obj) {
	var productId = $(obj).data("product-id");

	$.ajax({
		type : 'post',
		dataType : 'json',
		data : {
			'productId' : productId,
			'productSpecId':productSpecId

		},
		url :  '/cart/add',
		success : function(result) {
			if (result.status === 'SUCCESS') {
				window.location.href =  '/cart/addSuccess?pageTitle='+encodeURI($('#goodsName').text());
			} else {
				layer.alert(result.errMsg, {
					icon : 2
				});
			}
		}
	})
}
//查看全部评论
$('#allComments').click(function(){
    var id = $('#id').val();
    layer.open({
        type : 2,
        area : [ '900px','650px' ],
        shadeClose : true,
        shade : false,
        anim : 1, //0-6的动画形式，-1不开启
        maxmin : false, //开启最大化最小化按钮
        fix : false, //不固定
        scrollbar : false, //屏蔽游览器滚动条
        title : '产品评价',
        content : '../comment/list?productId='+id+'&pageNo=1'
    });
});
/**
 * 商品提问
 */
function add_question(obj) {
	var data = {};
	data.productId = productId;
	data.content = $(obj).prev().val()  ;
	layer.confirm('您确认提交此问题吗？', {
		btn : [ '确定', '取消' ] //按钮
	}, function() {
		$.ajax({
			type : 'post',
			dataType : 'json',
			data : data,
			url : baselocation + '/question/ask',
			success : function(result) {
				if (result.code == 1) {
					layer.msg('发表问题成功!', {
						icon : 1,
						time : 1000
					});
				} else if (result.code == 401) {
					window.location.href =  '/pass/login';
				} else {
					layer.alert(result.message, {
						icon : 2
					});
				}
			}
		})
	});
}