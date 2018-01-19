package com.play001.cloud.cms.entity;

import java.util.List;

public class InitialPreviewConfig {
    private String caption;
    private String width;
    public final String url = "/delete";
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
