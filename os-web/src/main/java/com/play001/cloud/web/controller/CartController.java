package com.play001.cloud.web.controller;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.web.service.impl.CartServiceImpl;
import com.play001.cloud.web.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private CategoryServiceImpl categoryService;
    /**
     * 加购物车后的回显界面
     * @param pageTitle 商品标题,需要显示的信息
     */
    @RequestMapping(value = "/addSuccess", method = RequestMethod.GET)
    public String addSuccess(String pageTitle, Model model) throws UnsupportedEncodingException, IException {
        pageTitle = URLDecoder.decode(pageTitle,"UTF-8");
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("categories", categoryService.findAllWithProduct());
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
}
