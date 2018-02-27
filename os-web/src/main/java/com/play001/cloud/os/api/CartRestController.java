package com.play001.cloud.os.api;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.CartService;
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
public class CartRestController {

    @Autowired
    private CartService cartService;



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(Long productId, Long productSpecId , @CookieValue("userJwt") String userJwt) throws Exception {
        return cartService.add(productId, productSpecId, userJwt);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(Long cartId, @CookieValue("userJwt") String userJwt) throws Exception {
        cartService.delete(cartId, userJwt);
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        responseEntity.setStatus(ResponseEntity.SUCCESS);
        return responseEntity;
    }
    @RequestMapping(value = "/getAmount", method = RequestMethod.GET)
    public ResponseEntity<Integer> getAmount(@CookieValue("userJwt") String userJwt) throws Exception {
        return new ResponseEntity<Integer>().setMessage(cartService.getAmount(userJwt));
    }
}
