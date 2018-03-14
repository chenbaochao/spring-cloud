package com.play001.cloud.cms.entity;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Arrays;

/**
 * BootStarp-fileInput 上传文件返回类
 */
public class UploadImageResponse implements Serializable {

    private String error;
    private String []initialPreview;
    private InitialPreviewConfig []initialPreviewConfig = new InitialPreviewConfig[1];

    public UploadImageResponse() {
        initialPreview = new String[1];
        initialPreviewConfig[0] = new InitialPreviewConfig();
    }

    public UploadImageResponse setError(String error) {
        this.error = error;
        return this;
    }

    public String[] getInitialPreview() {
        return initialPreview;
    }

    public void setInitialPreview(String initialPreview) {
        this.initialPreview[0] = initialPreview;
    }

    public InitialPreviewConfig[] getInitialPreviewConfig() {
        return initialPreviewConfig;
    }

    public void setInitialPreviewConfig(InitialPreviewConfig initialPreviewConfig) {
        this.initialPreviewConfig[0] = initialPreviewConfig;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


    public static class InitialPreviewConfig implements Serializable {
        //标题
        private String caption;
        //宽度
        private String width;
        public  String url;
        //主键ID
        private Long key;
        private String extra;

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public void setKey(Long key) {
            this.key = key;
            this.url = "/image/"+key;
        }

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        public Long getKey() {
            return key;
        }

    }
}