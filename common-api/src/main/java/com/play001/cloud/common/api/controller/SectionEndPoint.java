package com.play001.cloud.common.api.controller;

import com.play001.cloud.common.api.service.SectionService;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/section")
@RestController
public class SectionEndPoint {

    @Autowired
    private SectionService sectionService;
    /**
     * 首页section
     */
    @RequestMapping(value = "/getIndexSections", method = RequestMethod.GET)
    public Response<List<Section>> getIndexSections(){
        return  new Response<>(sectionService.getIndexSections());
    }
    /**
     * 页面header快捷导航
     */
    @RequestMapping(value = "/getHeaderSections", method = RequestMethod.GET)
    public Response<List<Section>> getHeaderSections(){
        return  new Response<>(sectionService.getHeaderSections());
    }

}
