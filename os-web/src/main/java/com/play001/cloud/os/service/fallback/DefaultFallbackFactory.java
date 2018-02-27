package com.play001.cloud.os.service.fallback;

import com.play001.cloud.support.entity.ResponseEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 容错Hystrix
 * 当请求网关出错时,设置默认返回值
 * 只要返回状态设置为error就可以了,具体反馈由各自controller决定
 */
@Component
public class DefaultFallbackFactory implements FallbackFactory {

    @Override
    public Object create(Throwable throwable){
        return new ResponseEntity<Integer>().setErrMsg("请求失败");
    }
}
