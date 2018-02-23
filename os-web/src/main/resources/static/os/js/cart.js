$(function(){
    initChecked();
    setTotalCount();
    setUserName();

    $('[name="status"]').click(function(){
        var status =  $(this).data('status');
        if(status !== 1) return;
        $(this).removeClass();
        var checked =  $(this).data('checked');
        if(checked === 1){
            $(this).addClass("iconfont icon-checkbox icon-checkbox J_itemCheckbox");
            $(this).data('checked',0);
            $('#J_selectAll').removeClass();
            $('#J_selectAll').addClass("iconfont icon-checkbox icon-checkbox J_itemCheckbox");
            $('#J_selectAll').data('checked',0);
        }else{
            $(this).addClass("iconfont icon-checkbox icon-checkbox-selected J_itemCheckbox");
            $(this).data('checked',1);
        }
        setTotalCount();
    });

    $('#J_selectAll').click(function () {
        $(this).removeClass();
        //全选
        if($(this).data('checked') === 0){
            $(this).data('checked', 1);
            $(this).addClass("iconfont icon-checkbox icon-checkbox-selected J_itemCheckbox");
            $('[name="status"]').each(function(){
                var status =  $(this).data('status');
                var checked =  $(this).data('checked');
                if(status === 1){
                    if(checked === 0){
                        $(this).removeClass();
                        $(this).addClass("iconfont icon-checkbox icon-checkbox-selected J_itemCheckbox");
                        $(this).data('checked',1);
                    }
                }
            });
        }else{
            //取消全选
            $(this).data('checked', 0);
            $(this).addClass("iconfont icon-checkbox icon-checkbox J_itemCheckbox");
            $('[name="status"]').each(function(){
                var status =  $(this).data('status');
                var checked =  $(this).data('checked');
                if(status === 1){
                    if(checked === 1){
                        $(this).removeClass();
                        $(this).addClass("iconfont icon-checkbox icon-checkbox J_itemCheckbox");
                        $(this).data('checked',0);
                    }
                }

            });
        }
        setTotalCount();
    });
    //去结账
    $('#J_goCheckout').click(function(){
        var carts = '';
        $('[name="status"]').each(function(){
            var checked =  $(this).data('checked');
            if(checked === 1){
                if(carts !==''){
                    carts+='&';
                }
                carts+='cartId='+$(this).data('cart-id');
            }
        });
        if(carts.length > 0){
            window.location.href = '../order/checkout?'+carts;
        }
    });
    function setTotalCount(){
        var totalPrice = 0;
        var selectNum = 0;
        $('[name="status"]').each(function(){
            var checked =  $(this).data('checked');
            if(checked === 1){
                selectNum++;
                var price = Number($(this).data('price'));
                totalPrice+=price;
            }
        });
        if(selectNum === 0){
            $('#J_noSelectTip').removeClass('hide');
            $('#J_goCheckout').removeClass('btn-primary').addClass('btn-disabled');
        }else{
            $('#J_noSelectTip').addClass("hide");
            $('#J_goCheckout').addClass('btn-primary').removeClass('btn-disabled');
        }
        $('#J_cartTotalPrice').text(totalPrice);
        $('#J_selTotalNum').text(selectNum);
    }
    function setUserName(){
        var cookie = $.cookie('userJwt');
        if(cookie !== null && cookie !== undefined){
            var strs = cookie.split('.');
            if(strs !== null && strs.length === 3){
                var credential = eval('('+window.atob(strs[1])+')');
                var expiryData = credential.expiryDate;//有效期
                var nowTime = Date.parse(new Date());
                if(expiryData > nowTime){//未过期
                    var username = credential.username;
                    $('#J_username').text(username);
                }

            }
        }
    }
    //选中有效的购物车
    function initChecked(){
        $('[name="status"]').each(function(index){
            var status =  $(this).data('status');
            if(status === 1){
                $(this).data('checked', 1);
                $(this).removeClass();
                $(this).addClass("iconfont icon-checkbox icon-checkbox-selected J_itemCheckbox");
            }else {
                $('.pre-info').each(function(i){
                    if(i === index){
                        if(status === 2){
                            $(this).html('库存不足');
                        }else{
                            $(this).html("商品已下架");
                        }
                    }
                });
            }
        });
    }
});

