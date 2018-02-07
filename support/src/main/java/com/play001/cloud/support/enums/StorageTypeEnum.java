package com.play001.cloud.support.enums;

/**
 * 上传工具类别 枚举
 */
public enum StorageTypeEnum {

    //七牛云存储
    QINIU(1,"QINIU"),
    //阿里云存储
    ALIYUN(2,"ALIYUN");

    private String name;
    private Integer index;
    StorageTypeEnum(int index, String name){
        this.index = index;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
