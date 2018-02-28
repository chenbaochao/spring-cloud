package com.play001.cloud.cms.api;

import com.baidu.ueditor.ActionEnter;
import com.play001.cloud.cms.entity.UploadImageResponse;
import com.play001.cloud.cms.service.ImageService;
import com.play001.cloud.cms.service.MenuService;
import com.play001.cloud.support.entity.Image;
import com.play001.cloud.support.entity.Menu;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 公用API
 */
@RestController
public class CommonRestController {


    private ImageService imageService;
    private MenuService menuService;

    @Autowired
    public void init(ImageService imageService, MenuService menuService){
        this.imageService = imageService;
        this.menuService = menuService;
    }

    /**
     * 上传图片
     * @param avatar_data 图片裁剪参数
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public UploadImageResponse uploadImage(MultipartFile upFile , String avatar_data){
        UploadImageResponse response = new UploadImageResponse();
        ResponseEntity<Image> responseEntityImage =  imageService.upload(upFile, HtmlUtils.htmlUnescape(avatar_data));
        //将返回数据转换为bootsrap-fileinput框架所固定的格式
        if(Objects.equals(responseEntityImage.getStatus(), ResponseEntity.ERROR)){
            response.setError(responseEntityImage.getErrMsg());
        }else{
            Image image = responseEntityImage.getMessage();
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
        ResponseEntity<Image> responseEntity = imageService.upload(upFile, null);
        Map<String, String> responseMap = new HashMap<>();
        //将返回数据转换为Ueditor框架所固定的格式
        if(Objects.equals(responseEntity.getStatus(), ResponseEntity.ERROR)){
            responseMap.put("state", "ERROR");
        }else{
            responseMap.put("state", "SUCCESS");
            responseMap.put("url", responseEntity.getMessage().getUrl());
            responseMap.put("size", String.valueOf(upFile.getSize()));
            responseMap.put("original", responseEntity.getMessage().getUrl());
            responseMap.put("type", upFile.getContentType());
        }
        return responseMap;
    }

    @RequestMapping(value = "/test")
    public List<Menu> test(){
        List<Menu> menus=null;
        return menus;
    }
    /**
     * 编辑产品中,删除相册旧图片时(已保存在数据库中图片)时不能直接删除图片,必须点击保存后在后台进行处理
     * 但是前端框架必须需要返回成功信息,所以在这里假回复一下
     * error 为空则表示成功
     */
    @RequestMapping(value = "/deleteSuccess")
    public Map<String, String>  deleteSuccess(){
        Map<String, String> response = new HashMap<>();
        response.put("error","");
        return response;
    }

}
