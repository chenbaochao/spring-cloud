package com.play001.cloud.os.service;

import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.os.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    /**
     * 加购????
     * @param productId 产品Id
     * @param productSpecId 服务规格ID
     * @param userJwt 用户口令
     */
    public ResponseEntity<Integer> add(Long productId, Long productSpecId, String userJwt) throws IException {
        ResponseEntity<Integer> responseEntity = cartMapper.add(productId, productSpecId, userJwt);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return new ResponseEntity<Integer>().setStatus(ResponseEntity.SUCCESS);
    }

    /**
     *  列出所有数据
     */
    public List<ShopCart> list(String userJwt) throws IException {
        ResponseEntity<List<ShopCart>> responseEntity = cartMapper.list(userJwt);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        return responseEntity.getMessage();
    }

    /**
     * 删除操作
     */
    public void delete(Long cartId, String userJwt) throws IException {
        ResponseEntity<Integer> responseEntity = cartMapper.delete(cartId, userJwt);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
    }

    public List<ShopCart> findById(Long cartId[], String userJwt) throws IException {
        if(cartId==null || cartId.length == 0){
            throw new IException("参数错误");
        }
        List<ShopCart> shopCarts = new ArrayList<>(cartId.length);
        for(Long id:cartId){
            ResponseEntity<ShopCart> responseEntity = cartMapper.findById(id, userJwt);
            if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
                throw new IException("查询购物车失败");
            }
            ShopCart shopCart = responseEntity.getMessage();
            if(shopCart == null){
                throw new IException("没有对应的购物车数据");
            }
            shopCarts.add(shopCart);
        }
        return shopCarts;
    }
}
