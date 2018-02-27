package com.play001.cloud.product.api.serivce;

import com.play001.cloud.product.api.mapper.CartMapper;
import com.play001.cloud.product.api.mapper.ProductMapper;
import com.play001.cloud.product.api.mapper.SpecificationMapper;
import com.play001.cloud.support.entity.*;
import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.user.ShopCart;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.support.entity.user.UserCredential;
import com.play001.cloud.support.mapper.ImageMapper;
import com.play001.cloud.support.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private SpecificationMapper specificationMapper;
    /**
     * 加购物车
     *  @param productId 产品Id
     * @param productSpecId 服务规格Id
     */
    @Transactional
    public  ResponseEntity<Integer> add(String jwt, Long productId, Long productSpecId) throws IException, IOException {
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(productId == null){
            return responseEntity.setErrMsg("产品Id为空");
        }
        if(productSpecId == null){
            return responseEntity.setErrMsg("产品规格Id为空");
        }
        UserCredential userCredential = JwtUtil.getCredentialByJwt(jwt);
        //查找用户购物车是否已经有同产品,同服务规格的数据,有则购买数量+1,无则插入数据
        Long cartId = cartMapper.findShopCartId(productId, productSpecId, userCredential.getUserId());
        if(cartId != null){
            cartMapper.increaseBuyNumberByCartId(cartId, userCredential.getUserId());
            return responseEntity.setStatus(ResponseEntity.SUCCESS);
        }
        ShopCart shopCart = new ShopCart();
        User user = new User();
        user.setId(userCredential.getUserId());
        shopCart.setUser(user);
        //获取产品信息
        Product product = productMapper.findById(productId);
        if(product == null){
            return responseEntity.setErrMsg("产品已下架");
        }
        shopCart.setProduct(product);
        //找到对应的产品规格
        boolean flag = false;//是否找到对应的产品规格
        for(Product.Specification spec : product.getSpecs()){
            if(spec.getId().equals(productSpecId)){
                shopCart.setSpec(spec);
                flag = true;
                break;
            }
        }
        if(!flag)return responseEntity.setErrMsg("产品规格不存在");
        cartMapper.add(shopCart);
        //图片又被引用啦
        imageMapper.increaseCount(product.getThumb().getId());
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    //购物车列表
    @Transactional
    public List<ShopCart> list(String jwt) throws IOException, IException {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(jwt);
        List<ShopCart> shopCarts = cartMapper.listByUserId(userCredential.getUserId());
        /*
         * 1.遍历获取最新产品信息
         * 2.判断购物车信息和产品信息
         * 3.产品不存在或者产品规格不存在,则购物车状态置为STATUS_INVALID,并保存进数据库
         * 4.如果产品名称,封面,服务规格名称,价格跟购物车数据不一致,则更新购物车数据
         * 5.产品规格库存不足,购物车状态置为STATUS_NO_STOCK
         */

        for(ShopCart cart: shopCarts){
            //只判断当前有效的数据是否失效
            if(!cart.getStatus().equals(ShopCart.STATUS_INVALID)){
                Product product = productMapper.findById(cart.getProduct().getId());
                //是否发生改变
                boolean isChange = false;
                //产品数据已经不存在了
                if(product == null){
                    isChange = true;
                    cart.setStatus(ShopCart.STATUS_INVALID);
                }else {
                    if(!product.getThumb().getId().equals(cart.getProduct().getThumb().getId())){
                        //更改购物车封面时,需要将图片的引用-1
                        imageMapper.decreaseCount(cart.getProduct().getThumb().getId());
                        cart.setProduct(product);
                        isChange = true;
                    }else if(!product.getName().equals(cart.getProduct().getName())){
                        cart.setProduct(product);
                        isChange = true;
                    }
                    //服务规格是否存在
                    Product.Specification spec = specificationMapper.findById(cart.getSpec().getId());
                    if(spec == null){
                        isChange = true;
                        cart.setStatus(ShopCart.STATUS_INVALID);
                    }else{
                        //库存不足
                        if(spec.getStock() < 1){
                            isChange = true;
                            cart.setStatus(ShopCart.STATUS_NO_STOCK);
                        }
                        if(!spec.getPrice().equals(cart.getSpec().getPrice())){
                            isChange = true;
                            cart.setSpec(spec);
                        }
                    }
                }
                if(isChange){
                    cartMapper.update(cart);
                }
            }

        }
        return shopCarts;
    }

    @Transactional
    public void delete(Long cartId, String jwt) throws IOException {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(jwt);
        ShopCart shopCart = cartMapper.findById(cartId, userCredential.getUserId());
        if(shopCart !=null){
            imageMapper.decreaseCount(shopCart.getId());
            cartMapper.delete(cartId, userCredential.getUserId());
        }
    }

    public ResponseEntity<ShopCart> findById(Long id, String userJwt){
        ResponseEntity<ShopCart> responseEntity = new ResponseEntity<>();
        try {
            UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
            ShopCart shopCart = cartMapper.findById(id ,userCredential.getUserId());
            if(shopCart == null){
                return responseEntity.setErrMsg("购物车消失啦");
            }
            return responseEntity.setMessage(shopCart);
        }catch (Exception e){
            e.printStackTrace();
            return responseEntity.setErrMsg("操作失败");
        }
    }

    //获取数量
    public ResponseEntity<Integer> getAmount(String userJwt) throws IOException {
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        return new ResponseEntity<Integer>().setMessage(cartMapper.getAmount(userCredential.getUserId()));
    }
}
