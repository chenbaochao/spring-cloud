$(function() {
	/*
	show_star_product(); // 明星单品
	show_top_category(); // 置顶分类
	show_hot_category(); // 热门分类
	show_popular_product(); // 为你推荐
	show_comment_product(); // 热评产品
	*/
    show_star_product(); // 明星单品
    show_hot_category(); // 热门分类
    show_header();
    indexSiteHeaderInit();
});


/**
 * 必须在siteheader加载完成后启用
 */
function indexSiteHeaderInit(){
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
 * 明星单品
 */
function show_star_product() {
	$.ajax({
		url :  "/starProduct",
		type : 'get',
		dataType : 'text',
		success : function(result) {
			$("#J_starProduct").html(result);
		}
	});
}

/**
 * 为你推荐
 */
function show_popular_product() {
	$.ajax({
		url :  "/recommend/popular",
		type : 'get',
		dataType : 'text',
		success : function(result) {
			$("#J_popularPrudoct").html(result);
		}
	});
}

/**
 * 热评产品
 */
function show_comment_product() {
	$.ajax({
		url :  "/recommend/comment",
		type : 'get',
		dataType : 'text',
		success : function(result) {
			$("#J_commentPrudoct").html(result);
		}
	});
}

/**
 * 置顶分类
 */
function show_top_category() {
	$.ajax({
		url : baselocation + "/recommend/top",
		type : 'get',
		dataType : 'text',
		success : function(result) {
			$("#J_topCategory").html(result);
		}
	});
}

/**
 * 热门分类
 */
function show_hot_category() {
	$.ajax({
		url : "/section",
		type : 'get',
        async: false,
		dataType : 'text',
		success : function(result) {
			$("#J_hotCategory").html(result);
		}
	});
}

/**
 * 轮播图
 */
$(function() {
	//代码初始化
	var size = $('.ull li').length;
	for (var i = 1; i <= size; i++) {
		var li = "<li></li>";
		$('.oll').append(li);
	}
	;
	//手动控制轮播图
	$('.ull li').eq(0).show();
	$('.oll li').eq(0).addClass('active');
	$('.oll li').mouseover(function() {
		$(this).addClass('active').siblings().removeClass('active');
		var index = $(this).index();
		i = index;
		$('.ull li').eq(index).stop().fadeIn(300).siblings().stop().fadeOut(300);
	})
	//自动播放
	var i = 0;
	var t = setInterval(move, 1500);
	//自动播放核心函数

	function move() {
		i++;
		if (i == size) {
			i = 0;
		}
		$('.oll li').eq(i).addClass('active').siblings().removeClass('active');
		$('.ull li').eq(i).fadeIn(300).siblings().fadeOut(300);
	}

	//向右播放核心函数

	function moveL() {
		i--;
		if (i == -1) {
			i = size - 1;
		}
		$('.oll li').eq(i).addClass('active').siblings().removeClass('active');
		$('.ull li').eq(i).fadeIn(300).siblings().fadeOut(300);
	}
	$('.box .left').click(function() {
		moveL();
	})
	$('.box .right').click(function() {
		move();
	})
	//鼠标移入移除
	$('.box').hover(function() {
		clearInterval(t);
	}, function() {
		t = setInterval(move, 1500);
	})
})