package com.play001.cloud.os.service;

import com.play001.cloud.os.mapper.CartMapper;
import com.play001.cloud.os.mapper.OrderMapper;
import com.play001.cloud.os.mapper.ProductMapper;
import com.play001.cloud.support.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    //下单.返回订单编号
    public ResponseEntity<Long> order(ArrayList<Long> cartIds, String userJwt, Long addressId){
        ResponseEntity<Long> responseEntity = new ResponseEntity<>();
        if(cartIds == null || cartIds.size() == 0 || userJwt == null || addressId == null){
            return responseEntity.setErrMsg("参数缺失");
        }
        return orderMapper.order(cartIds, userJwt, addressId);
    }
    //下单成功后,获取订单信息
    public Order findById(Long id, String userJwt) throws IException {
        ResponseEntity<Order> responseEntity = orderMapper.findById(id, userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }

    //下单.返回订单编号
    public ResponseEntity<Integer> pay(Long id, String userJwt){
        return orderMapper.pay(id, userJwt);
    }
    //订单列表
    public void list(Integer type, Model model, Integer pageNo, String userJwt) throws IException {
        if(type == null || type < 1 || type > 4){
            type = 0;
        }
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        ResponseEntity<Pagination<Order>> responseEntity = orderMapper.list(type, pageNo, userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        Pagination<Order> pagination = responseEntity.getMessage();
        if(pageNo > pagination.getTotalPage()){
            pageNo = 1;
        }
        List<Order> orders = pagination.getData();
        String url = "../order/list?type="+type+"&page="+pageNo;
        model.addAttribute("url", url);
        model.addAttribute("orders", orders);
        model.addAttribute("type", type);
        model.addAttribute("pagination", pagination);
    }
    //获取待支付的订单数量
    public Integer getUnPaidOrderAmount(String userJwt) throws IException {
        ResponseEntity<Integer> responseEntity = orderMapper.getUnPaidOrderAmount(userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }
    //获取待收货的订单数量
    public Integer getUnReceiveOrderAmount(String userJwt) throws IException {
        ResponseEntity<Integer> responseEntity = orderMapper.getUnReceiveOrderAmount(userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }
    //获取待评价的订单数量
    public Integer getUnCommentOrderAmount(String userJwt) throws IException {
        ResponseEntity<Integer> responseEntity = orderMapper.getUnCommentOrderAmount(userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }
    //确认收货
    public ResponseEntity<Integer> setReceive(Long id, String userJwt){
        return orderMapper.setReceive(id, userJwt);
    }
    //评价商品
    public ResponseEntity<Integer> comment(List<Comment> comments, String userJwt) {
        return orderMapper.comment(comments, userJwt);
    }
}
