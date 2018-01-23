package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.ProductMapper;
import com.play001.cloud.common.entity.*;
import com.play001.cloud.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加商品
     */
    @Transactional
    public Response<Integer> add(Product product){
        product.setCreateTime(DateUtil.getTime());
        product.setSoldNumber(0);
        List<Parameter> parameters = product.getParameters();
        List<Specification> specs = product.getSpecs();
        //先保存产品从而获得产品的ID,才能保存其它数据
        productMapper.addProduct(product);
        //保存参数
        for(Parameter para : parameters){
            para.setProductId(product.getId());
            productMapper.addPara(para);
        }
        //保存规格
        for(Specification spec : product.getSpecs()){
            spec.setProductId(product.getId());
            spec.setSoldNumber(0);
            productMapper.addSpec(spec);
        }
        //保存相册
        for(ProductImage productImage : product.getPics()){
            productImage.setProductId(product.getId());
            productMapper.addPic(productImage);
        }
        //保存标签
        for(String label : product.getLabels()){
            productMapper.addLabel(product.getId(), label);
        }
        //保存产品介绍
        productMapper.addIntroduction(product.getId(), product.getIntroduction());
        return new Response<Integer>().setStatus(Response.SUCCESS);
    }
}
