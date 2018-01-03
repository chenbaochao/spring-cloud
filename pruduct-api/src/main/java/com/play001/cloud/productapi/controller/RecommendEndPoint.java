package com.play001.cloud.productapi.controller;

import com.play001.cloud.common.entity.Product;
import com.play001.cloud.productapi.serivce.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/recommend")
public class RecommendEndPoint {

    @Autowired
    private RecommendService recommendService;
    /**
     * 明星产品
     */
    @RequestMapping(value = "/getStartProduct", method = RequestMethod.GET)
    public List<Product> getStartProduct(Model model){
        return recommendService.getStartProduct();
    }
}
