package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.*;
import com.play001.cloud.common.entity.*;
import com.play001.cloud.common.enums.StorageTypeEnum;
import com.play001.cloud.common.util.DateUtil;
import com.play001.cloud.common.util.storage.IBaseStorageUtil;
import com.play001.cloud.common.util.storage.StorageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private ProductLabelMapper productLabelMapper;
    @Autowired
    private ProductParaMapper productParaMapper;
    @Autowired
    private ProductSpecMapper productSpecMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private StorageFactory storageFactory;

    /**
     * 修改商品
     */
    public Response<Integer> update(Product newProduct){

        Response<Integer> response = new Response<>();
        if(newProduct.getId() == null){
            return response.setErrMsg("参数错误");
        }
        //待删除的图片,事务执行完成后从云端删除
        List<Image> deleteImages = new LinkedList<>();
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //获取事务级别
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //事务状态,开始事务
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            //获取旧产品信息
            Product oldProduct = productMapper.findById(newProduct.getId());
            //保存产品基本信息
            productMapper.update(newProduct);
            productMapper.updateIntroduction(newProduct);
            //判断封面是否更改,更改后需要将旧封面图片数据存进待删除的图片list中,并设置图片为已用
            if(!Objects.equals(oldProduct.getThumb().getId(), newProduct.getThumb().getId())){
                deleteImages.add(oldProduct.getThumb());
                imageMapper.setUsed(newProduct.getThumb().getId());
            }
            //相册
            List<ProductImage> oldProductImages = oldProduct.getPics();
            /* 查询存在于旧相册中不存在于新相册中的数据(被删除的productImage数据)
             *  然后删除os_ProductImage中的数据,并把对应的image状态设置为未用
             *  不能直接删除存储的图片文件,否者事务执行失败,回滚的话,图片数据是无法回滚的
             */
            boolean flag;
            for(ProductImage oldImage:oldProductImages){
                flag = false;
                for(ProductImage newImage:newProduct.getPics()){
                    if(Objects.equals(oldImage.getId(), newImage.getId())){
                        flag = true;
                    }
                }
                if(!flag){
                    productImageMapper.delete(oldImage.getId());
                    //待删除的图片Id,事务执行完成后从云端删除
                    deleteImages.add(oldImage.getImage());

                }
            }
            //保存新的相册
            for(ProductImage newImage:newProduct.getPics()){
                //新增的相册
                if(newImage.getId() == null){
                    newImage.setProductId(newProduct.getId());
                    productImageMapper.add(newImage);

                }
            }
            /*
             * 删除标签
             */
            List<Label> labels = oldProduct.getLabels();
            for(Label oldLabel:labels){
                flag = false;
                for(Label newLabel:newProduct.getLabels()){
                    if(Objects.equals(oldLabel.getId(), newLabel.getId())){
                        flag = true;
                    }
                }
                if(!flag){
                    productLabelMapper.delete(oldLabel.getId());
                }
            }
            //保存标签
            for(Label label:newProduct.getLabels()){
                if(label.getId() == null || label.getId() < 1 ){
                    label.setProductId(newProduct.getId());
                    productLabelMapper.add(label);
                }else{
                    productLabelMapper.update(label);
                }
            }
            //删除产品参数
            List<Parameter> Parameters = oldProduct.getParameters();
            for(Parameter oldPara:Parameters){
                flag = false;
                for(Parameter newPara:newProduct.getParameters()){
                    if(Objects.equals(oldPara.getId(), newPara.getId())){
                        flag = true;
                    }
                }
                if(!flag){
                    productParaMapper.delete(oldPara.getId());
                }
            }
            //保存产品参数
            for(Parameter parameter : newProduct.getParameters()){
                if(parameter.getId() == null || parameter.getId() < 1){
                    parameter.setProductId(newProduct.getId());
                    productParaMapper.add(parameter);
                }else{
                    productParaMapper.update(parameter);
                }
            }
            //删除产品规格
            List<Specification> specs = oldProduct.getSpecs();
            for(Specification oldSpec:specs){
                flag = false;
                for(Specification newSpec:newProduct.getSpecs()){
                    if(Objects.equals(oldSpec.getId(), newSpec.getId())){
                        flag = true;
                    }
                }
                if(!flag){
                    productSpecMapper.delete(oldSpec.getId());
                }
            }
            //产品规格
            for(Specification specification : newProduct.getSpecs()){
                if(specification.getId() == null || specification.getId() < 1){
                    specification.setProductId(newProduct.getId());
                    productSpecMapper.add(specification);
                }else{
                    productSpecMapper.update(specification);
                }
            }
            transactionManager.commit(status);
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
            return response.setErrMsg("删除失败");
        }
        //删除图片文件和数据
        IBaseStorageUtil storageUtil;
        for(Image image : deleteImages){
            try{
                storageUtil =  storageFactory.getStorageUtil(StorageTypeEnum.valueOf(image.getStorageName()));
                if(storageUtil.delete(image.getPath())){
                    //删除成功则删除数据库中的记录
                    imageMapper.delete(image.getId());
                }else{
                    //删除失败,图片记录置为未使用
                    imageMapper.setUnused(image.getId());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return response.setStatus(Response.SUCCESS);
    }
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
            productParaMapper.add(para);
        }
        //保存规格
        for(Specification spec : product.getSpecs()){
            spec.setProductId(product.getId());
            spec.setSoldNumber(0);
            productSpecMapper.add(spec);
        }
        //保存相册
        for(ProductImage productImage : product.getPics()){
            productImage.setProductId(product.getId());
            productImageMapper.add(productImage);
            imageMapper.setUsed(productImage.getImage().getId());
        }
        //保存标签
        for(Label label : product.getLabels()){
            label.setProductId(product.getId());
            productLabelMapper.add(label);
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
        //排序默认按照创建时间排序
        switch (sort){
            case "showPrice":
            case "soldNumber":
            case "createTime":
            case "status":
                break;
            default:
                sort = "createTime";

        }
        Map<String, Object> data = new HashMap<>();
        data.put("total", productMapper.count(categoryId));
        data.put("rows", productMapper.getList(offset, limit, sort, order, categoryId));
        return data;
    }

    public Product findById(Long id){
        return productMapper.findById(id);
    }
}
