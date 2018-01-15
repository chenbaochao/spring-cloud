package com.play001.cloud.cms.controller.rest;

import com.play001.cloud.cms.service.ImageService;
import com.play001.cloud.common.entity.Image;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

/**
 * 公用API
 */
@RestController
public class CommonRestController {

    @Autowired
    private ImageService imageService;

    /**
     * 上传图片
     * @param avatar_data 图片裁剪参数
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public Response<Image> avatar(MultipartFile upFile , String avatar_data){
        return imageService.upload(upFile, HtmlUtils.htmlUnescape(avatar_data));
    }

}
