package com.play001.cloud.web.service.serviceImpl;


import com.google.gson.Gson;
import com.play001.cloud.web.entity.IException;
import com.play001.cloud.web.entity.Product;
import com.play001.cloud.web.response.Response;
import com.play001.cloud.web.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;

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
        //String spec = new Gson().toJson(response.getMessage().getSpecifications());
        //model.addAttribute("spec", spec);
        model.addAttribute("product", response.getMessage());
    }

}
