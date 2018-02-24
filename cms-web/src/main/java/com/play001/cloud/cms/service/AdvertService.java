package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.AdvertMapper;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {

    @Autowired
    private AdvertMapper advertMapper;


    public List<Advert> findByCategoryId(Integer categoryId){
        return advertMapper.listByCategoryId(categoryId);
    }
    //增加
    public ResponseEntity<Integer> add(Advert advert){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        //分类为3的需要sectionId
        if(advert.getAdvertCategory().getId() == 3 && (advert.getSection() == null || advert.getSection().getId() == null)){
            return responseEntity.setErrMsg("分栏Id错误");
        }
        advertMapper.add(advert);;
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    //修改
    public ResponseEntity<Integer> update(Advert advert){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        //分类为3的需要sectionId
        if(advert.getId() == null ||advert.getAdvertCategory().getId() == 3 && (advert.getSection() == null || advert.getSection().getId() == null)){
            return responseEntity.setErrMsg("参数错误");
        }
        advertMapper.update(advert);;
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    //删除
    public ResponseEntity<Integer> delete(Integer id){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");

        }
        advertMapper.delete(id);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }
    //设置状态
    public ResponseEntity<Integer> setStatus(Integer id, Byte status){
        ResponseEntity<Integer> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");

        }
        advertMapper.setStatus(id, status);
        return responseEntity.setStatus(ResponseEntity.SUCCESS);
    }

    public Advert findById(Integer id) throws IException {
        if(id == null){
            throw new IException("参数错误");
        }
        Advert advert = advertMapper.findById(id);
        if (advert == null){
            throw new IException("数据不存在");
        }
        return advert;
    }
}
