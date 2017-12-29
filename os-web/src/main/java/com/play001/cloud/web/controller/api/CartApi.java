package com.play001.cloud.web.controller.api;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.UserCredential;
import com.play001.cloud.common.util.JwtUtil;
import com.play001.cloud.web.service.CartService;
import com.play001.cloud.web.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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

}
