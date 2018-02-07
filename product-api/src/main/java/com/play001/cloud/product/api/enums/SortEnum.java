package com.play001.cloud.product.api.enums;

public enum  SortEnum {
    //1.新品,2.销量,3.价格up,4.价格down
    NEW(1), SOLD(2), PRICE_UP(3), PRICE_DOWN(4);
    int value;

    SortEnum(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
