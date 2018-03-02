package com.play001.cloud.product.api.serivce;


import com.play001.cloud.product.api.enums.SortEnum;
import com.play001.cloud.support.entity.*;
import com.play001.cloud.product.api.mapper.ProductMapper;
import com.play001.cloud.support.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {




    private ProductMapper productMapper;

    private RedisTemplate<Long, Object> redisTemplate;

    @Autowired
    @SuppressWarnings("unchecked")
    public ProductService(ProductMapper productMapper, RedisTemplate redisTemplate) {
        this.productMapper = productMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 查找
     */
    public ResponseEntity<Product> findById(Long id) {
        //产品数据缓存有效期300秒,单位秒
        final long timeout = 60*5;
        ResponseEntity<Product> responseEntity =  new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        Product product = (Product)redisTemplate.opsForValue().get(id);
        if(product == null){
            product = productMapper.findById(id);
            if(product != null){
                //缓存产品数据
                redisTemplate.opsForValue().set(id, product, timeout, TimeUnit.SECONDS);
            }
        }else{
            //更新缓存有效期
            redisTemplate.expire(id, timeout, TimeUnit.SECONDS);
        }
        responseEntity.setMessage(product);
        return responseEntity;
    }


    public Pagination<Product> search(String keyword, Long start, Integer quantity){
        Pagination<Product> pagination = new Pagination<>();
        pagination.setTotalData(productMapper.countSearch(keyword));//数据总条数
        List<Product> products =  productMapper.search(keyword, start, quantity);
        pagination.setData(products);
        return pagination;
    }

    /**
     * 分类列出产品
     * @param categoryId 产品目录ID
     * @param sort 排序方式,1.新品,2.销量,3.价格up,4.价格down
     * @param start 开始位置
     * @param quantity 取数据条数
     */
    public Pagination<Product> listByCategoryId(Integer categoryId, Integer sort, Long start, Integer quantity){
        Pagination<Product> pagination = new Pagination<>();
        pagination.setTotalData(productMapper.countByCategory(categoryId));//数据总条数
        List<Product> products =  productMapper.listByCategoryId(categoryId, sort, start, quantity);
        pagination.setData(products);
        return pagination;
    }

    /**
     * 获取分类下的产品
     * @param categories 分类list
     * @param limit 每个每类下的数据限制条数
     */
    public ResponseEntity<List<Category>> findByCategory(List<Category> categories, Integer limit) {
        ResponseEntity<List<Category>> responseEntity = new ResponseEntity<>();
        for(Category category:categories){
            List<Product> products = productMapper.listByCategoryId(category.getId(), SortEnum.NEW.getValue(), 0L, limit);
            category.setProducts(products);
        }
        responseEntity.setMessage(categories);
        return responseEntity;
    }

    public void removeCachingById(Long productId){
        if(productId == null) return;
        redisTemplate.delete(productId);
    }
}
