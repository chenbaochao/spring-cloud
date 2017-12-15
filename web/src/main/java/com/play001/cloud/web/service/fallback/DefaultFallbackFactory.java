package com.play001.cloud.web.service.fallback;

import com.play001.cloud.web.response.Response;
import feign.hystrix.FallbackFactory;

/**
 * 容错Hystrix
 * 当请求网关出错时,设置默认返回值
 * 只要返回状态设置为error就可以了,具体反馈由各自controller决定
 */
public class DefaultFallbackFactory implements FallbackFactory {

    @Override
    public Object create(Throwable throwable){
        return new Response<>(Response.ERROR);
    }
}
