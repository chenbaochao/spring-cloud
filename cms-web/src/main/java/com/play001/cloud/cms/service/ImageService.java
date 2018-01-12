package com.play001.cloud.cms.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.play001.cloud.cms.mapper.ImageMapper;
import com.play001.cloud.cms.util.CommonUtil;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Image;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.util.storage.IBaseStorageUtil;
import com.play001.cloud.common.util.storage.StorageFactory;
import com.play001.cloud.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;

@Service
public class ImageService {

    @Autowired
    private StorageFactory storageFactory;
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private ImageMapper imageMapper;


    /**
     * 图片上传
     * 手动开启事务
     */
    public Response<Image> upload(MultipartFile upFile, String avatarData){
        Response<Image> response = new Response<>();
        if(upFile == null){
            return response.setErrMsg("参数错误");
        }
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //获取事务级别
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //事务状态,开始事务
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);

        Image image = new Image();
        image.setCreateTime(DateUtil.getTime());
        image.setUsed(false);
        try {
            //判断文件MIME type
            String type = upFile.getContentType();
            if(type == null || !type.toLowerCase().startsWith("image/")){
                return response.setErrMsg("不支持的文件类型，仅支持图片!");
            }
            //获取文件后缀
            String fileExt = CommonUtil.getFileExt(upFile.getOriginalFilename());
            if(fileExt == null || !CommonUtil.checkFileExt(fileExt)){
                return response.setErrMsg("不支持的文件类型");
            }
            StringBuilder uploadPath = new StringBuilder();
            //生成文件路径
            uploadPath.append("upload/image/").append(DateUtil.getYear()).append("/")
                    .append(DateUtil.getYear()).append("/")
                    .append(DateUtil.getMonth()).append("/")
                    .append(DateUtil.getDay()).append("/")
                    .append(System.currentTimeMillis()).append(new Random().nextInt(8999)+1000)
                    .append(".").append(fileExt);
            image.setPath(uploadPath.toString());
            //获取默认上传工具
            IBaseStorageUtil storageUtil = storageFactory.getDefaultStorageUtil();
            image.setStorageName(storageUtil.getStorageUtilType().getName());
            image.setUrl(storageUtil.getUrl()+uploadPath);
            //图片信息保存进数据库
            imageMapper.add(image);
            //裁剪图片,获得新的输入流
            InputStream imageInputStream = CommonUtil.cutImage(upFile, avatarData);

            if(!storageUtil.upload(imageInputStream, uploadPath.toString())){
                throw new IException("上传失败");
            }
            transactionManager.commit(status);
            return response.setMessage(image);
        } catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
            return response.setErrMsg("上传失败");
        }

    }
}