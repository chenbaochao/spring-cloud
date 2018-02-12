package com.play001.cloud.os.controller;

import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.os.service.CartService;
import com.play001.cloud.os.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private CategoryService categoryService;
    /**
     * 加购物车后的回显界面
     * @param pageTitle 商品标题,需要显示的信息
     */
    @RequestMapping(value = "/addSuccess", method = RequestMethod.GET)
    public String addSuccess(String pageTitle, Model model) throws UnsupportedEncodingException, IException {
        pageTitle = URLDecoder.decode(pageTitle,"UTF-8");
        model.addAttribute("pageTitle", pageTitle);
        return "product/add_cart_success";
    }

    /**
     * 购物车列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @CookieValue("userJwt")String userJwt) throws IException {
        model.addAttribute("carts", cartService.list(userJwt));
        return "product/cart_list";
    }

    /**
     * 导航栏购物车
     */
    @RequestMapping(value = "/topBar", method = RequestMethod.GET)
    public String topBar(Model model, @CookieValue("userJwt")String userJwt) throws IException {
        List<ShopCart> carts = cartService.list(userJwt);
        //计算总件数和总价格
        int totalNumber = 0, totalPrice = 0;
        for (ShopCart cart : carts){
            totalNumber+=cart.getBuyNumber();
            totalPrice+=cart.getBuyNumber()+cart.getSpec().getPrice();
        }
        model.addAttribute("carts", carts);
        model.addAttribute("totalNumber", totalNumber);
        model.addAttribute("totalPrice", totalPrice);
        return "product/cart_top_bar";
    }

}
