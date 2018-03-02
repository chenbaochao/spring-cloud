package com.play001.cloud.os.api;

import com.play001.cloud.os.service.OrderService;
import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @param cartId 购物车Id数组
     * @param userJwt 用户jwt
     * @param addressId 收件人地址Id
     *  返回订单Id
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Long> checkout(@RequestParam(value = "cartId[]")ArrayList<Long>cartId, @CookieValue("userJwt") String userJwt,@RequestParam(value = "addressId") Long addressId ) throws IException {
        return orderService.order(cartId, userJwt, addressId);
    }

    /**
     * 模拟付款
     * @param id 订单id

     */
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public ResponseEntity<Integer> pay(Long id, @CookieValue("userJwt")String userJwt){
        return orderService.pay(id, userJwt);
    }
    //收货
    @RequestMapping(value = "/setReceive", method = RequestMethod.POST)
    public ResponseEntity<Integer> setReceive(Long id, @CookieValue("userJwt")String userJwt){
        return orderService.setReceive(id, userJwt);
    }
    //评价商品
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<Integer> comment(@Valid @RequestBody List<Comment> comments, BindingResult result, @CookieValue("userJwt")String userJwt){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return orderService.comment(comments, userJwt);
    }
}
