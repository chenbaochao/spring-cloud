package com.play001.cloud.cms.api.advert;

import com.play001.cloud.cms.Interceptor.PermissionCode;
import com.play001.cloud.cms.service.AdvertCategoryService;
import com.play001.cloud.cms.service.AdvertService;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.AdvertCategory;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/advert")
class AdvertRestController {

    @Autowired
    private AdvertCategoryService advertCategoryService;
    @Autowired
    private AdvertService advertService;



    //广告列表

    /**
     *
     * @param categoryId 分类Id
     */
    @PermissionCode("advert_view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Advert> list(Integer categoryId){
        return advertService.findByCategoryId(categoryId);
    }

    //启用/停用广告
    @PermissionCode("advert_update")
    @RequestMapping(value = "/{id}/status/{status}", method = RequestMethod.PUT)
    public ResponseEntity<Integer> setValid(@PathVariable Integer id, @PathVariable Byte status){
        return advertService.setStatus(id, status);
    }

    //新增
    @PermissionCode("advert_create")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Integer> create(@Valid Advert advert, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return advertService.add(advert);
    }

    //修改
    @PermissionCode("advert_update")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Integer> update(@Valid Advert advert, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Integer>().setErrMsg("参数错误");
        }
        return advertService.update(advert);
    }

    //删除
    @PermissionCode("advert_delete")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> delete(@PathVariable Integer id){
        return advertService.delete(id);
    }

}
