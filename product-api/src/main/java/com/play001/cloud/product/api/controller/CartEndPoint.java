package com.play001.cloud.product.api.controller;

import com.play001.cloud.product.api.serivce.CartService;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.interceptor.UserPermissionVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * 购物车
 * 一起happy吧
 */
@RequestMapping("/cart")
@RestController
public class CartEndPoint {

    @Autowired
    private CartService cartService;
    /**
     * 加购物车
     * @param productId 产品Id
     * @param productSpecId 服务规格Id
     * @param userJwt 用户令牌
     * @return 成功Or失败
     */
    @UserPermissionVerify
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(Long productId, Long productSpecId, @RequestHeader("userJwt") String userJwt) throws IException, IOException {
        return cartService.add(userJwt, productId, productSpecId);
    }

    /**
     * 列出商品
     */
    @UserPermissionVerify
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ShopCart>> list(@RequestHeader("userJwt") String userJwt) throws IOException, IException {
        ResponseEntity<List<ShopCart>> responseEntity = new ResponseEntity<>();
        responseEntity.setMessage(cartService.list(userJwt));
        return responseEntity;
    }

    @UserPermissionVerify
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Long cartId, @RequestHeader("userJwt") String userJwt) throws IOException {
        if(cartId != null && cartId > 0){
            cartService.delete(cartId, userJwt);
        }
        return new ResponseEntity<>(ResponseEntity.SUCCESS);
    }

    @UserPermissionVerify
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<ShopCart> findById(Long id, @RequestHeader("userJwt") String userJwt){
        return cartService.findById(id, userJwt);
    }

}
