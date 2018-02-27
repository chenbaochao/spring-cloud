package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.Pagination;
import com.play001.cloud.support.entity.Product;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "ZUUL", fallback =  ProductMapper.ProductFallback.class)
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
    @Component
    static class ProductFallback implements ProductMapper{

        @Override
        public ResponseEntity<Product> getProduct(Long id) {
            return new ResponseEntity<Product>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Pagination<Product>> search(String keyword, Long start, Integer quantity) {
            return new ResponseEntity<Pagination<Product>>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<Pagination<Product>> listByCategoryId(Integer categoryId, Integer sort, Long start, Integer quantity) {
            return new ResponseEntity<Pagination<Product>>().setErrMsg("网络繁忙");
        }

        @Override
        public ResponseEntity<List<Product>> getStarProduct() {
            return new ResponseEntity<List<Product>>().setErrMsg("网络繁忙");
        }
    }
}
