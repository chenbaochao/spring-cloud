$(function() {
    //question_help(); // 默认显示最有帮助商品提问
    //comment_sup(); // 最有帮助评价
    //comment_time_line(); // 最新评价
    show_topBar();
    show_footer();
    show_header();
    siteHeaderInit();
});


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
}