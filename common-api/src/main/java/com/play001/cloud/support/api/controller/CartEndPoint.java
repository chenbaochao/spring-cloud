package com.play001.cloud.support.api.controller;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.ShopCart;
import com.play001.cloud.support.entity.UserCredential;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.interceptor.UserPermissionVerify;
import com.play001.cloud.support.util.JwtUtil;
import com.play001.cloud.support.api.service.CartService;
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
        if(productId == null) throw new IException("产品Id为空");
        if(productSpecId == null) throw new IException("产品规格Id为空");
        UserCredential credential = JwtUtil.getCredentialByJwt(userJwt);
        cartService.add(productId, productSpecId, credential.getUserId());
        return new ResponseEntity<Integer>(ResponseEntity.SUCCESS);
    }

    /**
     * 列出商品
     */
    @UserPermissionVerify
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<ShopCart>> list(@RequestHeader("userJwt") String userJwt) throws IOException {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        ResponseEntity<List<ShopCart>> responseEntity = new ResponseEntity<>();
        responseEntity.setMessage(cartService.list(userCredential.getUserId()));
        return responseEntity;
    }
    @UserPermissionVerify
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Long cartId, @RequestHeader("userJwt") String userJwt) throws IOException {
        if(cartId != null && cartId > 0){
            UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
            cartService.delete(cartId, userCredential.getUserId());
        }
        return new ResponseEntity<>(ResponseEntity.SUCCESS);
    }
}
