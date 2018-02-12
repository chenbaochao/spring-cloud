package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 导航,导航栏
 */
@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface NavigationMapper {

    @RequestMapping(value = "/common/navigation/getTopBarNavigationBars", method = RequestMethod.GET)
    ResponseEntity<List<NavigationBar>> getTopBarNavigationBars();

    @RequestMapping(value = "/common/navigation/getChannelNavigationBars", method = RequestMethod.GET)
    ResponseEntity<List<NavigationBar>> getChannelNavigationBars();
}
