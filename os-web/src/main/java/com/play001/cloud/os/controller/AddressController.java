package com.play001.cloud.os.controller;

import com.play001.cloud.os.service.UserAddressService;
import com.play001.cloud.support.entity.IException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/address")
@Controller
public class AddressController {

    @Autowired
    private UserAddressService userAddressService;
    //收货地址
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String addressList(Model model, @CookieValue("userJwt")String userJwt) throws IException {
        model.addAttribute("addresses", userAddressService.getAll(userJwt));
        return "usercenter/user_address";
    }
}
