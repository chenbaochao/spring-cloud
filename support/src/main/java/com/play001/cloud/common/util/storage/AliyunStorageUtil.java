package com.play001.cloud.common.util.storage;

import com.play001.cloud.common.enums.StorageTypeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Component
public class AliyunStorageUtil implements IBaseStorageUtil {
    @Override
    public Boolean upload(MultipartFile upFile, String path) {
        return null;
    }

    @Override
    public Boolean upload(InputStream upFile, String path) {
        return null;
    }

    @Override
    public Boolean delete(String path) {
        return null;
    }

    @Override
    public StorageTypeEnum getStorageUtilType() {
        return StorageTypeEnum.ALIYUN;
    }

    @Override
    public String getUrl() {
        return null;
    }
}
