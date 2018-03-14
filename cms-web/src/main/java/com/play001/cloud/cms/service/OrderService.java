package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.order.OrderMapper;
import com.play001.cloud.support.entity.Order;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    /**
     *
     * @param offset 开始位置
     * @param limit 数据条数
     * @param status 状态 0表示全部
     * @param order 倒序 正序
     * @param sort 排序依据
     */
    public Map<String, Object> getList(Long offset, Integer limit, String sort, String order, Byte status) {
        Long total = orderMapper.count(status);
        List<Order> orders = orderMapper.list(offset, limit, status,order,sort);
        Map<String, Object> result = new HashMap<>(2);
        result.put("total", total);
        result.put("rows", orders);
        return result;
    }

    /**
     * 设置状态为已发货(待收货)状态
     * 只有订单状态为1(代发货时)才能设置
     * @param id 订单Id
     * @return
     */
    public ResponseEntity<Integer> setStatusUnReceive(Long id){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        Byte status = orderMapper.getStatus(id);
        if(status == null){
            return responseEntity.setErrMsg("订单不存在");
        }
        if(status != 2){
            return responseEntity.setErrMsg("订单状态错误");
        }
        orderMapper.updateStatus(id,Order.STATUS_UNRECEIVE);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
}
