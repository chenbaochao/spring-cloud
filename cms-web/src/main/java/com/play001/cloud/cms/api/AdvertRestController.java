package com.play001.cloud.cms.api;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.AdvertCategoryService;
import com.play001.cloud.cms.service.AdvertService;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.AdvertCategory;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/advert")
public class AdvertRestController {

    @Autowired
    private AdvertCategoryService advertCategoryService;
    @Autowired
    private AdvertService advertService;

    //广告分类列表
    @PermissionCode("advert_view")
    @RequestMapping(value = "/category/getList", method = RequestMethod.GET)
    public  List<AdvertCategory> getCategoryList(){
        return advertCategoryService.findAll();
    }

    //广告列表
    @PermissionCode("advert_view")
    @RequestMapping(value = "/listByCategoryId", method = RequestMethod.GET)
    public List<Advert> listByCategoryId(Integer categoryId){
        return advertService.findByCategoryId(categoryId);
    }

    //启用广告
    @PermissionCode("advert_update")
    @RequestMapping(value = "/setValid", method = RequestMethod.POST)
    public ResponseEntity<Integer> setValid(Integer id){
        return advertService.setStatus(id, (byte)1);
    }

    //停用广告
    @PermissionCode("advert_update")
    @RequestMapping(value = "/setInvalid", method = RequestMethod.POST)
    public ResponseEntity<Integer> setInvalid(Integer id){
        return advertService.setStatus(id, (byte)0);
    }

    //新增
    @PermissionCode("advert_create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(@Valid Advert advert, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return advertService.add(advert);
    }

    //修改
    @PermissionCode("advert_update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@Valid Advert advert, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return advertService.update(advert);
    }

    //删除
    @PermissionCode("advert_delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Integer id){
        return advertService.delete(id);
    }

}