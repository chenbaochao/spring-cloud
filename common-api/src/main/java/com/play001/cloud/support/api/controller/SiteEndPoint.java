package com.play001.cloud.support.api.controller;

import com.play001.cloud.support.api.service.SiteService;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.SiteConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/site")
public class SiteEndPoint {

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/getConf", method = RequestMethod.GET)
    public ResponseEntity<SiteConf> getConf(){
        return siteService.getConf();
    }
}
