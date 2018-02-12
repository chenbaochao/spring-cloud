package com.play001.cloud.os.service;

import com.play001.cloud.os.mapper.CartMapper;
import com.play001.cloud.os.mapper.OrderMapper;
import com.play001.cloud.os.mapper.ProductMapper;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.Order;
import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
    public ResponseEntity<Long> order(Long cartIds[], String userJwt, Long addrssId){
        ResponseEntity<Long> responseEntity = new ResponseEntity<>();
        if(cartIds == null || cartIds.length == 0 || userJwt == null || addrssId == null){
            return responseEntity.setErrMsg("参数缺失");
        }
        return orderMapper.add(cartIds, userJwt, addrssId);
    }
    //下单成功后,获取订单信息
    public Order findById(Long id, String userJwt) throws IException {
        ResponseEntity<Order> responseEntity = orderMapper.findById(id, userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }
    //订单列表
    public void list(Integer type, Model model, Integer pageNo, String userJwt) throws IException {
        if(type == null || type < 1 || type > 4){
            type = 1;
        }
        ResponseEntity<Pagination<Order>> responseEntity = orderMapper.list(type, pageNo, userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        Pagination<Order> pagination = responseEntity.getMessage();
        if(pageNo < 1 || pageNo > pagination.getPageQuantity()){
            pageNo = 1;
        }
        List<Order> orders = pagination.getData();
        String url = "../order/list?type="+type+"&page="+pageNo;
        model.addAttribute("url", url);
        model.addAttribute("orders", orders);
        model.addAttribute("pagination", pagination);
    }
}
