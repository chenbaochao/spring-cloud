package com.play001.cloud.web.service.impl;


import com.play001.cloud.web.entity.IException;
import com.play001.cloud.web.entity.Product;
import com.play001.cloud.web.response.Response;
import com.play001.cloud.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductService productService;

    /**
     * 获取商品详情
     * @param id 商品Id
     */
    public void getDetial(Long id, Model model) throws IException {
        Response<Product> response = productService.getProduct(id);
        if(response.getStatus().equals(Response.ERROR)) throw new IException(response.getErrMsg());
        model.addAttribute("product", response.getMessage());
    }

    /**
     * 搜索
     * @param keyword 关键字
     * @param model
     */
    public void search(Integer pageNo, String keyword, Model model) {

    }
}
