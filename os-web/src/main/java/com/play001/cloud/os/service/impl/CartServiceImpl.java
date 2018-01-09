package com.play001.cloud.os.service.impl;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.ShopCart;
import com.play001.cloud.os.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl {

    @Autowired
    private CartService cartService;
    /**
     * 加购????
     * @param productId 产品Id
     * @param productSpecId 服务规格ID
     * @param userJwt 用户口令
     */
    public void add(Long productId, Long productSpecId, String userJwt) throws IException {
        Response<String> response = cartService.add(productId, productSpecId, userJwt);
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
    }

    /**
     *  列出所有数据
     */
    public List<ShopCart> list(String userJwt) throws IException {
        Response<List<ShopCart>> response = cartService.list(userJwt);
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }

    /**
     * 删除操作
     */
    public void delete(Long cartId, String userJwt) throws IException {
        Response<Integer> response = cartService.delete(cartId, userJwt);
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
    }
}
