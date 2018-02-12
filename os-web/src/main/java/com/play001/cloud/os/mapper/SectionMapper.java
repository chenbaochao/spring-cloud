package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface SectionMapper {

    @RequestMapping(value = "/common/section/getIndexSections", method = RequestMethod.GET)
    ResponseEntity<List<Section>> getIndexSections();

    @RequestMapping(value = "/common/section/getHeaderSections", method = RequestMethod.GET)
    ResponseEntity<List<Section>> getHeaderSections();
}
