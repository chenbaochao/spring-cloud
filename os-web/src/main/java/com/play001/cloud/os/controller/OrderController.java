package com.play001.cloud.os.controller;

import com.play001.cloud.os.service.CartService;
import com.play001.cloud.os.service.OrderService;
import com.play001.cloud.os.service.UserAddressService;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Order;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.support.entity.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CartService cartService;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private OrderService orderService;

    //下单检查
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(Long cartId[], @CookieValue("userJwt") String userJwt, Model model) throws IException {
        List<UserAddress> userAddresses = userAddressService.getAll(userJwt);
        List<ShopCart> shopCarts = cartService.findById(cartId, userJwt);
        model.addAttribute("userAddresses", userAddresses);
        model.addAttribute("shopCarts", shopCarts);
        //商品总价
        long totalPrice = 0;
        //商品总件数
        int totalCount = 0;
        for(ShopCart shopCart : shopCarts){
            totalPrice +=shopCart.getSpec().getPrice();
            totalCount+=shopCart.getBuyNumber();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("totalCount", totalCount);
        return "order/order_buy_checkout";
    }

    /**
     * 下单成功
     * @param id 订单Id
     * @param userJwt 用户jwt
     *  返回订单Id
     */
    @RequestMapping(value = "/orderSuccess", method = RequestMethod.GET)
    public String orderSuccess(Long id, @CookieValue("userJwt") String userJwt, Model model) throws IException {
        Order order = orderService.findById(id, userJwt);
        if(!order.getStatus().equals(Order.STATUS_UN_PAY)){
            throw new IException("订单状态错误");
        }
        model.addAttribute("order", order);
        return "order/order_order_success";
    }

   @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Integer pageNo, Integer type, Model model, @CookieValue("userJwt")String userJwt) throws IException {
        orderService.list(type, model, pageNo, userJwt);
        return "order/order_list";
   }
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, Model model, @CookieValue("userJwt")String userJwt) throws IException {
        model.addAttribute("order", orderService.findById(id, userJwt));
        return "order/order_view";
    }
    //评论页面
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public String comment(Long id, Model model, @CookieValue("userJwt")String userJwt) throws IException {
        model.addAttribute("order", orderService.findById(id, userJwt));
        return "order/order_comment";
    }
}
