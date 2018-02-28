package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.SectionCategoryMapper;
import com.play001.cloud.cms.mapper.SectionDetailMapper;
import com.play001.cloud.cms.mapper.SectionMapper;
import com.play001.cloud.support.entity.Category;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.support.entity.user.SectionCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;

@Service
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private SectionCategoryMapper sectionCategoryMapper;
    @Autowired
    private SectionDetailMapper sectionDetailMapper;
    @Autowired
    private DataSourceTransactionManager transactionManager;

    public Section findById(Integer id) throws IException {
        if(id == null){
            throw new IException("参数错误");
        }
        Section section = sectionMapper.findById(id);
        if(section == null){
            throw new IException("数据不存在");
        }
        return section;
    }
    public List<Section> listByCategory(Integer categoryId){
        return sectionMapper.listByCategory(categoryId);
    }

    //分类列表
    public List<SectionCategory> getCategoryList(){
        return sectionCategoryMapper.findAll();
    }
    //分类
    public SectionCategory findCategoryById(Integer categoryId) throws IException {
        if(categoryId == null){
            throw new IException("栏目分类不存在");
        }
        return sectionCategoryMapper.findById(categoryId);
    }
    //栏目列表
    public List<Section> getList(Integer categoryId){
        if(categoryId == null){
            return null;
        }
        return sectionMapper.listByCategory(categoryId);
    }
    //创建栏目
    public ResponseEntity<Integer> create(Section section){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        //开启事务
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);;
        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            //首先保存section从而获取sectionId
            sectionMapper.add(section);
            //保存显示的产品分类
            for(Category category:section.getCategories()){
                sectionDetailMapper.insert(section.getId(), category.getId());
            }
            transactionManager.commit(status);
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
            return responseEntity.setErrMsg("操作失败");
        }
        return responseEntity.setStatus(ResponseEntity.SUCCESS);

    }
    //更新栏目
    public ResponseEntity<Integer> update(Section section){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(section.getId() == null){
            return responseEntity.setErrMsg("缺少Id");
        }
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
        try {
            //更新section
            sectionMapper.update(section);
            //读出该section的产品分类,判断是否删除,新增
            List<Category> oldCategoryList = sectionDetailMapper.listBySectionId(section.getId());
            List<Category> newCategoryList = section.getCategories();
            //保存新增的
            for(Category newCategory : newCategoryList){
                boolean flag = false;
                for(Category oldCategory : oldCategoryList){
                    if(newCategory.getId().equals(oldCategory.getId())){
                        flag =  true;
                        break;
                    }
                }
                if(!flag){
                    sectionDetailMapper.insert(section.getId(), newCategory.getId());
                }
            }
            //删除
            for(Category oldCategory : oldCategoryList){
                boolean flag = false;
                for(Category newCategory : newCategoryList){
                    if(newCategory.getId().equals(oldCategory.getId())){
                        flag =  true;
                        break;
                    }
                }
                if(!flag){
                    sectionDetailMapper.deleteOne(section.getId(), oldCategory.getId());
                }
            }
            transactionManager.commit(status);
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
        }
        return responseEntity.setStatus(ResponseEntity.SUCCESS);

    }

    //删除
    @Transactional
    public ResponseEntity<Integer> delete(Integer id) {
        sectionMapper.delete(id);
        sectionDetailMapper.deleteBySectionId(id);
        return new ResponseEntity<Integer>().setStatus(ResponseEntity.SUCCESS);
    }

    public ResponseEntity<Integer> setStatus(Integer id, Byte status) {
        sectionMapper.setStatus(id, status);
        return new ResponseEntity<Integer>().setStatus(ResponseEntity.SUCCESS);
    }
}
