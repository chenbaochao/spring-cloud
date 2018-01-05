package com.play001.cloud.commonapi.controller;

import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.Section;
import com.play001.cloud.commonapi.service.SectionService;
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

    @RequestMapping(value = "/getSection", method = RequestMethod.GET)
    public Response<List<Section>> getSection(){
        return  new Response<>(sectionService.getIndexSections());
    }
}
