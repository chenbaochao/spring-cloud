package com.play001.cloud.cms.service;

import com.play001.cloud.cms.mapper.ImageMapper;
import com.play001.cloud.cms.util.CommonUtil;
import com.play001.cloud.support.entity.Image;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.enums.StorageTypeEnum;
import com.play001.cloud.cms.util.storage.IBaseStorageUtil;
import com.play001.cloud.cms.util.storage.StorageFactory;
import com.play001.cloud.support.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<Image> upload(MultipartFile upFile, String avatarData){
        ResponseEntity<Image> responseEntity = new ResponseEntity<>();
        if(upFile == null){
            return responseEntity.setErrMsg("参数错误");
        }
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        //获取事务级别
        transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        //事务状态,开始事务
        TransactionStatus status = transactionManager.getTransaction(transactionDefinition);

        Image image = new Image();
        image.setCreateTime(DateUtil.getTime());
        image.setCount(0L);
        try {
            //判断文件MIME type
            String type = upFile.getContentType();
            if(type == null || !type.toLowerCase().startsWith("image/")){
                return responseEntity.setErrMsg("不支持的文件类型，仅支持图片!");
            }
            //获取文件后缀
            String fileExt = CommonUtil.getFileExt(upFile.getOriginalFilename());
            if(fileExt == null || !CommonUtil.checkFileExt(fileExt)){
                return responseEntity.setErrMsg("不支持的文件类型");
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
            InputStream imageInputStream;
            //如果有裁剪参数就裁剪图片
            if(avatarData != null && avatarData.length() > 0){
                //裁剪图片,获得新的输入流
                imageInputStream = CommonUtil.cutImage(upFile, avatarData);
            }else{
                //将multipartfile转换为输入流
                BufferedImage src = ImageIO.read(upFile.getInputStream());
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(src,"jpg" , os);
                imageInputStream = new ByteArrayInputStream(os.toByteArray());
            }
            if(!storageUtil.upload(imageInputStream, uploadPath.toString())){
                return responseEntity.setErrMsg("上传失败");
            }
            transactionManager.commit(status);
            responseEntity.setMessage(image);
            return responseEntity;
        } catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback(status);
            return responseEntity.setErrMsg("上传失败");
        }

    }

    /**
     * 删除图片
     */
    public Map<String,String> delete(Long id) {
        Map<String, String> response = new HashMap<>();
        Image image;
        if(id == null ||  (image = imageMapper.findById(id)) == null){
            response.put("error", "图片不存在");
            return response;
        }

        IBaseStorageUtil storageUtil = storageFactory.getStorageUtil(StorageTypeEnum.valueOf(image.getStorageName()));
        storageUtil.delete(image.getPath());
        response.put("error", "");
        return response;
    }
}
