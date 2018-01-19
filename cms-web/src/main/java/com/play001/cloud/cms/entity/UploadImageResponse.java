package com.play001.cloud.cms.entity;

import com.google.gson.Gson;

import java.util.Arrays;

/**
 * BootStarp-fileInput 上传文件返回信息
 */
public class UploadImageResponse {

    private String error;
    private String []initialPreview;
    private InitialPreviewConfig []initialPreviewConfig;

    public UploadImageResponse() {
        initialPreview = new String[1];
        initialPreviewConfig = new InitialPreviewConfig[1];
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
}