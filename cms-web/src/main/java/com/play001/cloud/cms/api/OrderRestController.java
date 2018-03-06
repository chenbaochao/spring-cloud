package com.play001.cloud.cms.api;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.OrderService;
import com.play001.cloud.support.entity.Order;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/order")
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @PermissionCode("order_view")
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map<String, Object> getList(Long offset, Integer limit, String sort, String order, Byte status){
        return orderService.getList(offset, limit, sort, order, status);
    }

    /**
     * 设置状态为已发货(待收货)状态
     * 只有订单状态为1(代发货时)才能设置
     * @param id 订单Id
     */
    @PermissionCode("order_update")
    @RequestMapping(value = "/setStatusUnReceive", method = RequestMethod.POST)
    public ResponseEntity<Integer> setStatusUnReceive(Long id){
        return orderService.setStatusUnReceive(id);
    }
}
