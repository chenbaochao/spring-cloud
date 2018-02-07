package com.play001.cloud.support.api;

import com.play001.cloud.support.api.service.AdvertService;
import com.play001.cloud.support.api.service.NavigationService;
import com.play001.cloud.support.entity.AppInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 程序启动后,用redis缓存数据
 */
@Component
public class Init implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(Init.class);
    @Autowired
    private AdvertService advertService;
    @Autowired
    private NavigationService navigationService;
    @Autowired
    private AppInfo appInfo;
    @Override
    public void run(String... strings) throws Exception {
        logger.info("端口:"+appInfo.getPort()+" 已启动, id:"+ appInfo.getId());
        //缓存数据
        logger.info("初始化缓存数据-------------------");
        advertService.cachingSliderAdvert();
        advertService.cachingUnderSliderAdvert();
        navigationService.cachingChannelNavigationBars();
        navigationService.cachingTopBarNavigationBars();

    }
}
