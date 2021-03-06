$(function() {
	//showsectime(); //网站计时器
    show_topBar();
    show_footer();
})


/**
 * 必须在siteheader加载完成后启用
 */
function siteHeaderInit(){
    /**
     * 导航分类栏显示及颜色变换
     */
    $('#J_navCategory').mouseover(function() {
        $('.site-category').css('display', 'block');
    })
    $('#J_navCategory').mouseout(function() {
        $('.site-category').css('display', 'none');
    })

    /**
     * 轮播top菜单导航
     */
    $('.site-category .category-item').mouseover(function() {
        $(this).addClass('category-item-active').siblings().removeClass('category-item-active');
        var index = $(this).index();
        i = index;
        $('.children').eq(index).css('display', 'block');
    })
    $('.site-category .category-item').mouseout(function() {
        $(this).removeClass('category-item-active');
        var i = $(this).index();
        $('.children').eq(i).css('display', 'none');
    })
    /**
     * 初始化搜索框
     */
    initSearch();
}

/**
 * 获取并显示topBar
 */
function show_topBar() {
	var topBar = $('#site-topBar');
	if(topBar !== null){
        $.ajax({
            url : "/siteTopBar",
            type : 'get',
            dataType : 'text',
            success : function(result) {
                topBar.html(result);
                //加载完毕后判断是否登陆
                if(isLogin()){
                    //已经登陆显示购物车和用户信息
                    getCartList();
                    userInfo();
                    show_cart_umber(); // 购物车商品数量
                }
            }
        });

	}
}
/**
 * 获取并显示header
 */
function show_header() {
    var siteHeader = $('#site-header');
    if(siteHeader !== null){
        $.ajax({
            url : "/siteHeader",
            type : 'get',
			async:false,
            dataType : 'text',
            success : function(result) {
                siteHeader.html(result);
            }
        });
    }
}



/**
 * 获取并显示siteFooter
 */
function show_footer() {
    var footer = $('#site-footer');
    if(footer != null){
        $.ajax({
            url : "/siteFooter",
            type : 'get',
            dataType : 'text',
            success : function(result) {
                footer.html(result);
            }
        });
    }
}
/**
 * 网站计时器
 * @param {} str
 * @return {}
 */
function NewDate(str) {
	str = str.split('-');
	var date = new Date();
	date.setUTCFullYear(str[0], str[1] - 1, str[2]);
	date.setUTCHours(0, 0, 0, 0);
	return date;
}
function showsectime() {
	var birthDay = NewDate("2016-04-28");
	var today = new Date();
	var timeold = today.getTime() - birthDay.getTime();

	var sectimeold = timeold / 1000
	var secondsold = Math.floor(sectimeold);
	var msPerDay = 24 * 60 * 60 * 1000;

	var e_daysold = timeold / msPerDay;
	var daysold = Math.floor(e_daysold);
	var e_hrsold = (daysold - e_daysold) * -24;
	var hrsold = Math.floor(e_hrsold);
	var e_minsold = (hrsold - e_hrsold) * -60;
	var minsold = Math.floor((hrsold - e_hrsold) * -60);

	var seconds = Math.floor((minsold - e_minsold) * -60).toString();
	document.getElementById("showsectime").innerHTML = "网站运行：" + daysold + "天" + hrsold + "小时" + minsold + "分" + seconds + "秒";
	setTimeout(showsectime, 1000);

}

/**
 * 登陆与未登录的判断和显示不同的内容
 */
function isLogin(){
    var cookie = $.cookie('userJwt');
    if(cookie !== null && cookie !== undefined){
        var strs = cookie.split('.');
        if(strs !== null && strs.length === 3){
        	var credential = eval('('+window.atob(strs[1])+')');
        	var expiryData = credential.expiryDate;//有效期
        	var nowTime = Date.parse(new Date());
        	if(expiryData > nowTime){//未过期
                var username = credential.username;
                $('#LoginUsername').text(username);
                $('#J_userInfo').css('display','');
				$('#J_miniCartTrigger').css('display','');
                return true;
			}

		}
	}
    $('#noLogin').css('display','');
    return false;
}
/**
 * 搜索栏
 */
function initSearch(){
    $("#zySearch").zySearch({
        "width" : "355",
        "height" : "35",
        "parentClass" : "pageTitle",
        "callback" : function(keyword) {
            if(keyword !== null && keyword !== '')  window.location.href = '/product1/search?keyword='+encodeURI(keyword)+'&pageNo=1';
        }
    });
}

/**
 * 回到顶部
 */
$(function() {
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失  
	$(window).scroll(function() {
		if ($(window).scrollTop() > 100) {
			$("#back-to-top").fadeIn(1000);
		} else {
			$("#back-to-top").fadeOut(1000);
		}
	});

	//当点击跳转链接后，回到页面顶部位置  
	$("#back-to-top").click(function() {
		$('body,html').animate({
			scrollTop : 0
		}, 1000);
		return false;
	});
});

/**
 * 设置分类导航排序样式 
 */
$(function() {
	var url = window.document.location.pathname;
	$("a[href$='" + url + "']").parent().addClass("active").siblings().removeClass('active');
});



/**
 * input 聚焦事件
 */
$(function() {
	$(" input[ type='text' ] ").focus(function() {
		$(this).addClass('current').siblings().removeClass('current');
	});
	$(" input[ type='text' ] ").blur(function() {
		$(this).removeClass('current');
	});
});


/**
 * 展示用户登陆下拉菜单
 */
function userInfo(){
    $("#J_userInfo .user").hover(function() {
        $(this).addClass('user-active');
        $(this).children(".user-menu").css('display', 'block').animate({
            height : "165px"
        }, 165);
    }, function() {
        $(this).removeClass('user-active');
        $(this).children(".user-menu").css('display', 'none');
    })
}


/**
 * 导航栏购物车
 */
var lodingHtml = '<div style="text-align: center" class="tac"><img src="/os/images/loading.gif"></div>';
function getCartList(){
    $('.topbar-cart').hover(function() {
    	console.log("导航栏购物车");
        $('.site-topbar .cart-menu').css('display', 'block');
        $.ajax({
            url : "/cart/topBar",
            type : 'get',
            dataType : 'text',
            beforeSend : function() {
                $(".site-topbar .cart-menu").children(".loading").html(lodingHtml);
            },
            success : function(result) {
                $(".site-topbar .cart-menu").html(result);
                $('.site-topbar .cart-mini').css({
                    display : 'block',
                    background : '#fff',
                    color : '#ff6700'
                });
            }
        });
    }, function() {
        $('.site-topbar .cart-menu').css('display', 'none');
        var len = $('.site-topbar .cart-menu .cart-list').children('li').length;
        if (len > 0) {
            $('.site-topbar .cart-mini').css({
                background : '#ff6700',
                color : '#fff'
            });
        } else {
            $('.site-topbar .cart-mini').css({
                background : '#424242',
                color : '#b0b0b0'
            });
        }
    });
}


/**
 * 购物车删除商品
 */
function cart_delete(obj, data) {
	$.ajax({
		type : 'post',
		dataType : 'json',
		url : '/cart/delete?&cartId=' + data,
		success : function(result) {
			if (result.status == 'SUCCESS') {
				$(obj).parent().parent().parent("li").remove();
				show_cart_umber();
			} else {
				layer.alert(result.errMsg, {
					icon : 2
				});
			}
		}
	})
}

/**
 * 购物车商品数量
 */
function show_cart_umber() {
	$.ajax({
		url : "/cart/getAmount",
		type : 'get',
		dataType : 'json',
		success : function(result) {
			if (result.status === 'SUCCESS') {
				$('.site-topbar .cart-mini').css({
					background : '#ff6700',
					color : '#fff'
				});
				$('.site-topbar .cart-mini').children('span').text("（" + result.message + "）");
			} else {
				$('.site-topbar .cart-mini').css({
					background : '#424242',
					color : '#b0b0b0'
				});
				$('.site-topbar .cart-mini').children('span').text("（0） ");
			}
		}
	});
}