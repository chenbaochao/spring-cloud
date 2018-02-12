package com.play001.cloud.product.api.mapper.fallback;

import com.play001.cloud.support.entity.ResponseEntity;
import feign.hystrix.FallbackFactory;

/**
 * 容错Hystrix
 * 当请求网关出错时,设置默认返回值
 * 只要返回状态设置为error就可以了,具体反馈由各自controller决定
 */
public class DefaultFallbackFactory implements FallbackFactory {

    @Override
    public Object create(Throwable throwable){
        return new ResponseEntity<>().setErrMsg("请求失败");
    }
}
