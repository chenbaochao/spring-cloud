package com.play001.cloud.common.util.storage;

import com.play001.cloud.common.enums.StorageTypeEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件上传基类
 */
public interface IBaseStorageUtil {

    /**
     * 上传MultipartFile文件
     * @param upFile 带上传的文件
     * @param path 上传路径, 切勿以'\'开头
     * @return 上传是否成功
     */
     Boolean upload(MultipartFile upFile, String path);

    /**
     * 上传MultipartFile文件
     * @param upFile 带上传的文件
     * @param path 上传路径, 切勿以'\'开头
     * @return 上传是否成功
     */
    Boolean upload(InputStream upFile, String path);


    /**
     * 删除文件
     * @param path 删除文件的路径
     */
     Boolean delete(String path);

    /**
     * 获取工具类型
     */
    StorageTypeEnum getStorageUtilType();

    /**
     * 获取配置的URL
     */
    String getUrl();



}
