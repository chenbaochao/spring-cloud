package com.play001.cloud.cms.controller.rest;

import com.play001.cloud.cms.entity.UploadImageResponse;
import com.play001.cloud.cms.service.ImageService;
import com.play001.cloud.common.entity.Image;
import com.play001.cloud.common.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 公用API
 */
@RestController
public class CommonRestController {


    private ImageService imageService;

    @Autowired
    public void init(ImageService imageService){
        this.imageService = imageService;
    }

    /**
     * 上传图片
     * @param avatar_data 图片裁剪参数
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public UploadImageResponse uploadImage(MultipartFile upFile , String avatar_data){
        return imageService.upload(upFile, HtmlUtils.htmlUnescape(avatar_data));
    }

    /**
     * 删除图片
     * 返回NULL删除成功,返回map中error存储出错信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, String> delete(Long key){
        return imageService.delete(key);
    }

    @RequestMapping(value = "/uploadTest", method = RequestMethod.POST)
    public Response<Image> uploadTest(MultipartFile upFile){
        Response<Image> response = new Response<>();
        response.setStatus(Response.ERROR);
        response.setErrMsg("上传失败");
        return response;
    }

}
