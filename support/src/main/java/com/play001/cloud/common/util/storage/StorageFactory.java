package com.play001.cloud.common.util.storage;

import com.play001.cloud.common.enums.StorageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 上传工具工厂
 */
@Service
public class StorageFactory {

    @Autowired
    private QiniuStorageUtil qiniuUploadUtil;
    @Autowired
    private AliyunStorageUtil aliyunStorageUtil;


    @Value("${storage-util.default-storage-util-name}")
    private String defaultStorageUtilName;//默认存储工具名称

    //存储工具容器
    private Map<StorageTypeEnum, IBaseStorageUtil> storageUtilMap = new HashMap<>();


    //注入完成后需要进行的初始化操作
    @PostConstruct
    public void init(){
        storageUtilMap.put(qiniuUploadUtil.getStorageUtilType(), qiniuUploadUtil);
        storageUtilMap.put(aliyunStorageUtil.getStorageUtilType(), aliyunStorageUtil);

    }

    /**
     * 获取默认存储工具
     */

    public IBaseStorageUtil getDefaultStorageUtil(){
        return storageUtilMap.get(StorageTypeEnum.valueOf(defaultStorageUtilName));
    }
    /**
     * 获取指定存储工具
     */
    public IBaseStorageUtil getStorageUtil(StorageTypeEnum storageTypeEnum){
        return storageUtilMap.get(storageTypeEnum);
    }

}
