package com.play001.cloud.cms.controller.rest;

import com.baidu.ueditor.ActionEnter;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        UploadImageResponse response = new UploadImageResponse();
        Response<Image> responseImage =  imageService.upload(upFile, HtmlUtils.htmlUnescape(avatar_data));
        //将返回数据转换为bootsrap-fileinput框架所固定的格式
        if(Objects.equals(responseImage.getStatus(), Response.ERROR)){
            response.setError(responseImage.getErrMsg());
        }else{
            response.setInitialPreview(responseImage.getMessage().getUrl());
            Image image = responseImage.getMessage();
            response.setInitialPreview(image.getUrl());
            response.getInitialPreviewConfig()[0].setCaption(upFile.getOriginalFilename());
            response.getInitialPreviewConfig()[0].setKey(image.getId());
        }
        return response;
    }

    /**
     * 删除图片
     * 返回NULL删除成功,返回map中error存储出错信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, String> delete(Long key){
        return imageService.delete(key);
    }

    /***
     * 获取Ueditor配置文件
     */
    @RequestMapping(value = "/getUeditorConf", method = RequestMethod.GET)
    public String getUeditorConf(HttpServletRequest request){
        String rootPath = CommonRestController.class.getClassLoader().getResource("").getPath();
        rootPath+="static/common/ueditor/config";
        rootPath = rootPath.substring(1);
        return new ActionEnter(request, rootPath).exec();
    }
    /**
     * Ueditor 上传图片
     * Ueditor上传图片和其它框架上传图片所上传的参数和返回信息格式有所不同,但是流程一样,故分开所写.调用同一个service函数
     */
    @RequestMapping(value = "/ueditor/uploadImage", method = RequestMethod.POST)
    public Map<String, String> ueditorUploadImage(MultipartFile upFile){
        Response<Image> response = imageService.upload(upFile, null);
        Map<String, String> responseMap = new HashMap<>();
        //将返回数据转换为Ueditor框架所固定的格式
        if(Objects.equals(response.getStatus(), Response.ERROR)){
            responseMap.put("state", "ERROR");
        }else{
            responseMap.put("state", "SUCCESS");
            responseMap.put("url", response.getMessage().getUrl());
            responseMap.put("size", String.valueOf(upFile.getSize()));
            responseMap.put("original", response.getMessage().getUrl());
            responseMap.put("type", upFile.getContentType());
        }
        return responseMap;
    }
}
