package com.play001.cloud.support.api.controller;

import com.play001.cloud.support.api.service.AdvertService;
import com.play001.cloud.support.entity.Advert;
import com.play001.cloud.support.entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 各种广告,推荐
 */
@RestController
@RequestMapping(value = "/advert")
public class AdvertEndPoint {


    @Autowired
    private AdvertService advertService;

    /**
     * 获取首页图片轮播数据
     */
    @RequestMapping(value = "/getSliderAdvert", method = RequestMethod.GET)
    public ResponseEntity<List<Advert>> getSliderAdvert(){
        return new ResponseEntity<>(advertService.getSliderAdvert());
    }

    /**
     * 获取首页图片轮播下面的advert
     */
    @RequestMapping(value = "/getUnderSliderAdvert", method = RequestMethod.GET)
    public ResponseEntity<List<Advert>> getUnderSliderAdvert(){
        return new ResponseEntity<>(advertService.getUnderSliderAdvert());
    }

}
