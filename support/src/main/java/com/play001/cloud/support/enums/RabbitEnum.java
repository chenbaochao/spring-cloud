package com.play001.cloud.support.enums;

/**
 * rabbit route kry
 * 事件类型
 */
public enum RabbitEnum {
    LOGIN("LOGIN"),
    PRODUCT_CHANGE("PRODUCT_CHANGE"),
    ADVERT_CHANGE("ADVERT_CHANGE"),
    SECTION_CHANGE("SECTION_CHANGE"),
    CATEGORY_CHANGE("CATEGORY_CHANGE"),
    NAVIGATION_CHANGE("NAVIGATION_CHANGE");

    private String routeKey;

    RabbitEnum(String routeKey) {
        this.routeKey = routeKey;
    }

    public String getRouteKey() {
        return routeKey;
    }

}
