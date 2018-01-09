package com.play001.cloud.os.controller.api;

import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车API接口
 */
@RestController
@RequestMapping("/cart")
public class CartApi {

    @Autowired
    private CartServiceImpl cartService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response<String> add(Long productId, Long productSpecId , @CookieValue("userJwt") String userJwt) throws Exception {
        cartService.add(productId, productSpecId, userJwt);
        Response<String> response = new Response<>();
        response.setStatus(Response.SUCCESS);
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Response<String> delete(Long cartId, @CookieValue("userJwt") String userJwt) throws Exception {
        cartService.delete(cartId, userJwt);
        Response<String> response = new Response<>();
        response.setStatus(Response.SUCCESS);
        return response;
    }
}
