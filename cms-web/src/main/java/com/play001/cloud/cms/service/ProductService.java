package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.ProductMapper;
import com.play001.cloud.common.entity.*;
import com.play001.cloud.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 添加商品
     */
    @Transactional
    public Response<Integer> create(Product product){
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

    /**
     * 获取产品列表
     * @param offset 开始位置
     * @param limit 数据条数
     * @param sort 排序条件
     * @param order 排序方式 desc/asc
     */
    public Map<String, Object> getList(Long offset, Integer limit, String sort, String order, Integer categoryId){
        if(categoryId == null ){
            categoryId = 0;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("total", productMapper.count(categoryId));
        data.put("rows", productMapper.getList(offset, limit, sort, order, categoryId));
        return data;
    }
}
