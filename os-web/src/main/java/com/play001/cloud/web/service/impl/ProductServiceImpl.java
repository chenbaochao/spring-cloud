package com.play001.cloud.web.service.impl;


import com.play001.cloud.web.entity.IException;
import com.play001.cloud.web.entity.Pagination;
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
     * @param pageNo 页面编号,从1开始
     * @param model
     */
    public void search(Integer pageNo, String keyword, Model model) throws IException {
        //一页显示二十个
        final Integer pageSize = 20;
        //计算开始位置
        Long start = Long.valueOf((pageNo-1)*pageSize-1);
        Response<Pagination<Product>> response =  productService.search(keyword, start, pageSize);
        if(Response.ERROR == response.getStatus()){
            throw new IException(response.getErrMsg());
        }
        Pagination<Product> pagination = response.getMessage();
        pagination.setPageNo(pageNo);
        pagination.setPageSize(pageSize);
        //计算总共有多少页
        pagination.setPageQuantity((pagination.getDataQuantity().intValue()+pageSize-1)/pageSize.intValue());
        model.addAttribute("pagination", pagination);
    }
}
