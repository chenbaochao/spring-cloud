package com.play001.cloud.common.util.storage;

import com.play001.cloud.common.enums.StorageTypeEnum;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.InputStream;

//七牛云附件管理工具
@Component
public class QiniuStorageUtil implements IBaseStorageUtil {

    @Value("${storage-util.qiniu.access-key}")
    private String accessKey;
    @Value("${storage-util.qiniu.secret-key}")
    private String secretKey;
    @Value("${storage-util.qiniu.url}")
    private String url;
    @Value("${storage-util.qiniu.bucket}")
    private String bucket;

    private  UploadManager uploadManager;//...上传组件
    private String upToken ;//...生成上传凭证，然后准备上传
    private BucketManager bucketManager;//管理组件

    /**
     *初始化
     */

    public QiniuStorageUtil() throws Exception {


    }

    @PostConstruct
    public void init() throws Exception {
        try {
            Configuration cfg = new Configuration(Zone.zone2());//构造一个带指定Zone对象的配置类
            uploadManager = new UploadManager(cfg);
            Auth auth = Auth.create(accessKey, secretKey);
            upToken = auth.uploadToken(bucket);
            bucketManager = new BucketManager(auth, cfg);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("初始化七牛云上传失败");
        }
    }

    /**
     * 上传
     * @param upFile 待上传文件
     * @param path 上传路径 example storage/image/2017/02/01/1111111111****.jpg
     */
    @Override
    public Boolean upload( MultipartFile upFile, String path){
        try {
            InputStream inputStream = upFile.getInputStream();//要上传的输入流
            uploadManager.put(inputStream, path, upToken, null, null);
            //解析上传成功的结果
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return  false;
        }
    }

    @Override
    public Boolean upload(InputStream upFile, String path) {
        try {
            uploadManager.put(upFile, path, upToken, null, null);
            //解析上传成功的结果
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return  false;
        }
    }


    //删除文件
    @Override
    public Boolean delete(String path){
        try {
            bucketManager.delete(bucket, path);
            return true;
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            return false;
        }

    }

    @Override
    public StorageTypeEnum getStorageUtilType() {
        return StorageTypeEnum.QINIU;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
