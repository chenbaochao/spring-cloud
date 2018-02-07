package com.play001.cloud.support.api.service;

import com.play001.cloud.support.api.mapper.CartMapper;
import com.play001.cloud.support.api.mapper.ProductMapper;
import com.play001.cloud.support.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    /**
     * 加购物车
     *  @param productId 产品Id
     * @param productSpecId 服务规格Id
     */
    public void add(Long productId, Long productSpecId, Long userId) throws IException {
        ShopCart shopCart = new ShopCart();
        User user = new User();
        user.setId(userId);
        shopCart.setUser(user);
        //获取产品信息
        ResponseEntity<Product> responseEntity = productMapper.findById(productId);
        if(ResponseEntity.ERROR.equals(responseEntity.getStatus())) throw new IException(responseEntity.getErrMsg());
        Product product = responseEntity.getMessage();
        shopCart.setProduct(product);
        //找到对应的产品规格
        boolean flag = false;//是否找到对应的产品规格
        for(Specification spec : product.getSpecs()){
            if(spec.getId().equals(productSpecId)){
                shopCart.setSpec(spec);
                flag = true;
                break;
            }
        }
        if(!flag) throw new IException("产品规格不存在");
        cartMapper.add(shopCart);
    }
    public List<ShopCart> list(Long userId){
        return cartMapper.list(userId);
    }

    public void delete(Long cartId, Long userId){
        cartMapper.delete(cartId, userId);
    }
}
