package com.play001.cloud.os.service;

import com.play001.cloud.common.entity.NavigationBar;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 导航,导航栏
 */
@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface NavigationService {

    @RequestMapping(value = "/common/navigation/getTopBarNavigationBars", method = RequestMethod.GET)
    Response<List<NavigationBar>> getTopBarNavigationBars();

    @RequestMapping(value = "/common/navigation/getNavigationBars1", method = RequestMethod.GET)
    Response<List<NavigationBar>> getNavigationBars1();
}
