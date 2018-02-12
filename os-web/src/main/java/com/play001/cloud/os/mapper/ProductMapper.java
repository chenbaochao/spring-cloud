package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.product.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface ProductMapper {

    @RequestMapping(value = "/product/getDetail", method = RequestMethod.GET)
    ResponseEntity<Product> getProduct(@RequestParam("id") Long id);

    @RequestMapping(value = "/product/search", method = RequestMethod.GET)
    ResponseEntity<Pagination<Product>> search(@RequestParam("keyword")String keyword,
                                               @RequestParam("start")Long start,
                                               @RequestParam("quantity") Integer quantity);

    @RequestMapping(value = "/product/listByCategoryId", method = RequestMethod.GET)
    ResponseEntity<Pagination<Product>> listByCategoryId(@RequestParam("categoryId")Integer categoryId,
                                                         @RequestParam("sort") Integer sort,
                                                         @RequestParam("start")Long start,
                                                         @RequestParam("quantity") Integer quantity);

    /**
     * 获取首页明星产品
     */
    @RequestMapping(value = "/product/getStarProduct", method = RequestMethod.GET)
    ResponseEntity<List<Product>> getStarProduct();
}
