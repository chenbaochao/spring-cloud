package com.play001.cloud.product.api.serivce;

import com.play001.cloud.product.api.mapper.*;
import com.play001.cloud.support.entity.*;
import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.Product.Specification;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.support.entity.user.UserAddress;
import com.play001.cloud.support.entity.user.UserCredential;
import com.play001.cloud.support.util.DateUtil;
import com.play001.cloud.support.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderAddressMapper orderAddressMapper;
    @Autowired
    private OrderProductMapper orderProductMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;


    //下单
    public ResponseEntity<Long> order(ArrayList<Long> cartIds, String userJwt, Long addressId){
        ResponseEntity<Long> responseEntity = new ResponseEntity<>();
        if(cartIds == null || cartIds.size() == 0 || addressId == null){
            return responseEntity.setErrMsg("参数错误");
        }
        Order order = new Order();
        order.setStatus(Order.STATUS_UN_PAY);
        UserCredential userCredential;
        try {
            userCredential = JwtUtil.getCredentialByJwt(userJwt);
            User user = new User();
            user.setId(userCredential.getUserId());
            order.setUser(user);
        } catch (IOException e) {
            e.printStackTrace();
            return responseEntity.setErrMsg("操作失败");
        }
        //获取收货地址
        ResponseEntity<UserAddress> userAddressResponse = userAddressMapper.findById(addressId, userJwt);

        if(userAddressResponse.getStatus().equals(ResponseEntity.ERROR)){
            return responseEntity.setErrMsg(userAddressResponse.getErrMsg());
        }
        UserAddress userAddress = userAddressResponse.getMessage();
        if(userAddress == null){
            return responseEntity.setErrMsg("收货地址不存在");
        }
        //开启事务
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);

        try {
            List<OrderProduct> orderProducts = new ArrayList<>(cartIds.size());
            //订单总价
            double totalPrice = 0L;

            for(Long cartId:cartIds){
                ShopCart shopCart = cartMapper.findById(cartId, userCredential.getUserId());
                if(shopCart == null){
                    throw new IException("没有找到对应的购物车数据");
                }
                Product product = productMapper.findById(shopCart.getProduct().getId());
                if(product == null || product.getStatus().equals(0)){
                    throw new IException("商品:"+shopCart.getProduct().getName()+"已下架");
                }
                shopCart.setProduct(product);
                Specification spec = specificationMapper.findById(shopCart.getSpec().getId());
                if(spec == null){
                    throw new IException("商品:"+shopCart.getProduct().getName()+", 规格"+shopCart.getSpec().getName()+",已下架");
                }
                if(spec.getStock() < 1){
                    throw new IException("来晚一步,商品:"+shopCart.getProduct().getName()+", 规格"+shopCart.getSpec().getName()+",已买完");
                }
                shopCart.setSpec(spec);
                OrderProduct orderProduct = new OrderProduct(shopCart);
                orderProduct.setProductAmount(spec.getPrice() * shopCart.getBuyNumber());
                totalPrice+=orderProduct.getProductAmount();
                Integer effectLineCount = 0;
                //库存-1,重试三次
                for(int i = 0; i < 3 && effectLineCount == 0; i++){
                    effectLineCount = specificationMapper.decreaseStock(shopCart.getSpec().getId());
                }
                if(effectLineCount == 0){
                    throw new IException("网络繁忙,请重试");
                }
                //检查完毕后删除购物车
                cartMapper.delete(shopCart.getId(), userCredential.getUserId());
                orderProducts.add(orderProduct);
            }
            order.setAmount(totalPrice);
            order.setCreateTime(DateUtil.getTime());
            /*
                先保存订单,从而获取订单Id
             */
            orderMapper.add(order);
            //保存收货地址
            orderAddressMapper.add(new OrderAddress(order.getId(), userAddress));
            //保存订单产品
            for(OrderProduct orderProduct:orderProducts){
                orderProduct.setOrderId(order.getId());
                orderProductMapper.add(orderProduct);
            }
            transactionManager.commit(status);
            return responseEntity.setMessage(order.getId());
        }catch (IException ie){
            //哥屋恩
            transactionManager.rollback(status);
            return responseEntity.setErrMsg(ie.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
            return responseEntity.setErrMsg("出错了,请重试");
        }
    }

    //查找订单
    public ResponseEntity<Order> findById(Long id, String userJwt) {
        ResponseEntity<Order> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        try {
            UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
            responseEntity.setMessage(orderMapper.findById(id, userCredential.getUserId()));
            return responseEntity;
        }catch (Exception e) {
            e.printStackTrace();
            return responseEntity.setErrMsg("网络繁忙");
        }

    }


    /**
     * 列表,分页
     * @param type 订单状态, -1为查询所有商品
     * @param pageNo 当前页面编号. 从1开始
     * @param userJwt 用户jwt
     */
    public ResponseEntity<Pagination<Order>> list(Integer type, Integer pageNo, String userJwt) throws IOException {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        ResponseEntity<Pagination<Order>> responseEntity = new ResponseEntity<>();
        //-1表示所有订单
        if(type == null || type < -1 || type > 4){
            return responseEntity.setErrMsg("分类错误");
        }
        if(pageNo == null || pageNo < 1){
            return responseEntity.setErrMsg("页数错误");
        }
        Pagination<Order> pagination = new Pagination<>();
        //默认一页显示20条数据
        final int defaultPageSize = 20;
        //数据总条数
        int totalCount = orderMapper.count(userCredential.getUserId(), type);
        long start = (long)(pageNo-1) * defaultPageSize;
        List<Order> orders = orderMapper.pagination(userCredential.getUserId(), start, defaultPageSize, type);
        //当前页面
        pagination.setPageNo(pageNo);
        //总页数
        pagination.setTotalPage((totalCount+totalCount-1)/defaultPageSize);
        pagination.setData(orders);
        return responseEntity.setMessage(pagination);
    }
    //模拟付款
    public ResponseEntity<Integer> pay(Long id, String userJwt) throws IOException {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        if(id == null){
            responseEntity.setErrMsg("订单id错误");
            return responseEntity;
        }
        orderMapper.setStatus(id, Order.STATUS_PAID, userCredential.getUserId());
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    //订单数量
    public ResponseEntity<Integer> countByStatus(byte status, String userJwt) throws IOException {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        return new ResponseEntity<Integer>().setMessage(orderMapper.countByStatus(status, userCredential.getUserId()));
    }

    //评价商品
    @Transactional
    public ResponseEntity<Integer> comment(List<Comment> comments, String  userJwt) throws IException {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(comments == null || comments.size() == 0){
            return responseEntity.setErrMsg("数据为空");
        }
        ResponseEntity<User> userResponseEntity = userMapper.getInfo(userJwt);
        if(userResponseEntity.getStatus().equals(ResponseEntity.ERROR)){
            return responseEntity.setErrMsg(userResponseEntity.getErrMsg());
        }
        User user = userResponseEntity.getMessage();

        Long orderId = comments.get(0).getOrderId();
        //获取订单
        Order order = orderMapper.findById(orderId, user.getId());
        if(order == null){
            return responseEntity.setErrMsg("订单不存在");
        }
        Byte status = orderMapper.getStatus(orderId, user.getId());
        if(status == null || status != Order.STATUS_UNCOMMENT){
            return responseEntity.setErrMsg("订单状态错误");
        }
        List<OrderProduct> orderProducts = order.getOrderProducts();
        //订单产品必须一次性全部评论
        for(OrderProduct orderProduct:orderProducts){
            boolean flag = false;
            //循环保存每个评论
            for(Comment comment : comments){
                if(comment.getOrderProductId().equals(orderProduct.getId())){
                    flag = true;
                    if(!comment.getOrderId().equals(orderId)){
                        throw new IException("数据错误:订单编号不唯一");
                    }
                    comment.setProductId(orderProduct.getProductId());
                    //用户数据
                    comment.setUser(user);
                    comment.setCreateTime(DateUtil.getTime());
                    //设置为显示
                    comment.setStatus(Comment.STATUS_SHOW);
                    //保存评论
                    commentMapper.add(comment);
                }
            }
            //订单产品为一次性全部评论
            if(!flag){
                throw new IException("数据缺少:订单产品必须一次性全部评论");
            }
        }
        //设置订单状态为已完成
        orderMapper.setStatus(orderId, Order.STATUS_COMPLETE, user.getId());
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    //确认收货
    public ResponseEntity<Integer> setReceive(Long id, String userJwt) throws IOException {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        UserCredential user = JwtUtil.getCredentialByJwt(userJwt);
        orderMapper.setStatus(id, Order.STATUS_UNCOMMENT, user.getUserId());
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
}
