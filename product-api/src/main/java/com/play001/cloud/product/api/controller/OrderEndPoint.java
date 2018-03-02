package com.play001.cloud.product.api.controller;

import com.play001.cloud.product.api.serivce.OrderService;
import com.play001.cloud.support.entity.*;
import com.play001.cloud.support.interceptor.UserPermissionVerify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderEndPoint {

    @Autowired
    private OrderService orderService;
    /**
     * 下单
     * @param cartId 购物车Id数组
     * @param userJwt 用户jwt
     * @param addressId 收件人地址Id
     *  返回订单Id
     */
    @UserPermissionVerify
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Long> order(@RequestParam("cartIds") ArrayList<Long> cartIds, @RequestHeader("userJwt") String userJwt, Long addressId ) throws IException {
        return orderService.order(cartIds, userJwt, addressId);
    }
    /**
     * findById
     * @param id 订单Id
     * @param userJwt 用户jwt
     *  返回订单Id
     */
    @UserPermissionVerify
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<Order> findById( Long id, @RequestHeader("userJwt") String userJwt) throws IException {
        return orderService.findById(id, userJwt);
    }
    /**
     * 列表,分页
     * @param type 订单状态, -1为查询所有商品
     * @param pageNo 当前页面编号. 从1开始
     * @param userJwt 用户jwt
     */
    @UserPermissionVerify
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public  ResponseEntity<Pagination<Order>> list(Integer pageNo, Integer type, @RequestHeader("userJwt")String userJwt) throws IException, IOException {
        return orderService.list(type, pageNo, userJwt);
    }
    //模拟支付
    @UserPermissionVerify
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public  ResponseEntity<Integer> pay(Long id, @RequestHeader("userJwt")String userJwt) throws IOException {
        return orderService.pay(id, userJwt);
    }
    //获取待支付的订单数量
    @UserPermissionVerify
    @RequestMapping(value = "/getUnPaidOrderAmount", method = RequestMethod.GET)
    public  ResponseEntity<Integer> getUnPaidOrderAmount(@RequestHeader("userJwt")String userJwt) throws IOException {
        return orderService.countByStatus(Order.STATUS_UN_PAY, userJwt);
    }
    //获取待收货的订单数量
    @UserPermissionVerify
    @RequestMapping(value = "/getUnReceiveOrderAmount", method = RequestMethod.GET)
    public  ResponseEntity<Integer> getUnReceiveOrderAmount(@RequestHeader("userJwt")String userJwt) throws IOException {
        return orderService.countByStatus(Order.STATUS_UNRECEIVE,userJwt);
    }
    //获取待评价的订单数量
    @UserPermissionVerify
    @RequestMapping(value = "/getUnCommentOrderAmount", method = RequestMethod.GET)
    public  ResponseEntity<Integer> getUnCommentOrderAmount(@RequestHeader("userJwt")String userJwt) throws IOException {
        return orderService.countByStatus(Order.STATUS_UNCOMMENT,userJwt);
    }
    //评价
    @UserPermissionVerify
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public ResponseEntity<Integer> comment(@RequestBody List<Comment> comments, @RequestHeader("userJwt")String userJwt) throws IException {
        return orderService.comment(comments,userJwt);
    }
    //确认收货
    @UserPermissionVerify
    @RequestMapping(value = "/setReceive", method = RequestMethod.POST)
    public ResponseEntity<Integer> setReceive(Long id, @RequestHeader("userJwt")String userJwt) throws IOException {
        return orderService.setReceive(id,userJwt);
    }

}
