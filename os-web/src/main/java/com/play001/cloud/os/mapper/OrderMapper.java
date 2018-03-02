package com.play001.cloud.os.mapper;

import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import com.play001.cloud.support.entity.Comment;
import com.play001.cloud.support.entity.Order;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "ZUUL", fallback = OrderMapper.OrderFallback.class)
public interface OrderMapper {

    //下订单,返回订单编号
    @RequestMapping(value = "/product/order/order", method = RequestMethod.POST)
    ResponseEntity<Long> order(@RequestParam("cartIds") ArrayList<Long> cartIds, @RequestHeader("userJwt") String userJwt, @RequestParam("addressId") Long addressId);

    //findById
    @RequestMapping(value = "/product/order/findById", method = RequestMethod.GET)
    ResponseEntity<Order> findById(@RequestParam("id") Long id, @RequestHeader("userJwt") String userJwt);
    //订单列表
    @RequestMapping(value = "/product/order/list", method = RequestMethod.GET)
    ResponseEntity<Pagination<Order>> list(@RequestParam("type")Integer type, @RequestParam("pageNo") Integer pageNo, @RequestHeader("userJwt") String userJwt);

    //模拟购物
    @RequestMapping(value = "/product/order/pay", method = RequestMethod.POST)
    ResponseEntity<Integer> pay(@RequestParam("id")Long id, @RequestHeader("userJwt") String userJwt);

    //获取待支付的订单数量
    @RequestMapping(value = "/product/order/getUnPaidOrderAmount", method = RequestMethod.GET)
    ResponseEntity<Integer> getUnPaidOrderAmount(@RequestHeader("userJwt")String userJwt);
    //获取待收货的订单数量
    @RequestMapping(value = "/product/order/getUnReceiveOrderAmount", method = RequestMethod.GET)
    ResponseEntity<Integer> getUnReceiveOrderAmount(@RequestHeader("userJwt")String userJwt);
    //获取待评价的订单数量
    @RequestMapping(value = "/product/order/getUnCommentOrderAmount", method = RequestMethod.GET)
    ResponseEntity<Integer> getUnCommentOrderAmount(@RequestHeader("userJwt")String userJwt);
    //确认收货
    @RequestMapping(value = "/product/order/setReceive", method = RequestMethod.POST)
    ResponseEntity<Integer> setReceive(@RequestParam("id")Long id, @RequestHeader("userJwt")String userJwt);
    //评论商品
    @RequestMapping(value = "/product/order/comment", method = RequestMethod.POST)
    ResponseEntity<Integer> comment(@RequestBody List<Comment> comments, @RequestHeader("userJwt")String userJwt);

    @Component
    static class OrderFallback implements OrderMapper{

        @Override
        public ResponseEntity<Long> order(ArrayList<Long> cartIds, String userJwt, Long addressId) {
            return new ResponseEntity<Long>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Order> findById(Long id, String userJwt) {
            return new ResponseEntity<Order>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Pagination<Order>> list(Integer type, Integer pageNo, String userJwt) {
            return new ResponseEntity<Pagination<Order>>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Integer> pay(Long id, String userJwt) {
            return new ResponseEntity<Integer>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Integer> getUnPaidOrderAmount(String userJwt) {
            return new ResponseEntity<Integer>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Integer> getUnReceiveOrderAmount(String userJwt) {
            return new ResponseEntity<Integer>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Integer> getUnCommentOrderAmount(String userJwt) {
            return new ResponseEntity<Integer>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Integer> setReceive(Long id ,String userJwt) {
            return new ResponseEntity<Integer>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Integer> comment(List<Comment> comments, String userJwt) {
            return new ResponseEntity<Integer>().setErrMsg("网络繁忙");
        }
    }


}
