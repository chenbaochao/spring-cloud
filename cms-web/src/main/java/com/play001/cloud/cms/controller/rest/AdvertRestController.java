package com.play001.cloud.cms.controller.rest;

import com.play001.cloud.cms.service.AdvertCategoryService;
import com.play001.cloud.cms.service.AdvertService;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.AdvertCategory;
import com.play001.cloud.support.entity.ResponseEntity;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/advert")
public class AdvertRestController {

    @Autowired
    private AdvertCategoryService advertCategoryService;
    @Autowired
    private AdvertService advertService;

    //广告分类列表
    @RequestMapping(value = "/category/getList", method = RequestMethod.GET)
    public  List<AdvertCategory> getCategoryList(){
        return advertCategoryService.findAll();
    }
    //广告列表
    @RequestMapping(value = "/listByCategoryId", method = RequestMethod.GET)
    public List<Advert> listByCategoryId(Integer categoryId){
        return advertService.findByCategoryId(categoryId);
    }

    //启用广告
    @RequestMapping(value = "/setValid", method = RequestMethod.POST)
    public ResponseEntity<Integer> setValid(Integer id){
        return advertService.setStatus(id, (byte)1);
    }
    //停用广告
    @RequestMapping(value = "/setInvalid", method = RequestMethod.POST)
    public ResponseEntity<Integer> setInvalid(Integer id){
        return advertService.setStatus(id, (byte)0);
    }
    //新增
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(@Valid Advert advert, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return advertService.add(advert);
    }
    //修改
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@Valid Advert advert, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return advertService.update(advert);
    }
    //新增
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Integer id){
        return advertService.delete(id);
    }

}
