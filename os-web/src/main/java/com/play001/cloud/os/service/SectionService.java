package com.play001.cloud.os.service;

import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.Section;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ZUUL", fallbackFactory = DefaultFallbackFactory.class)
public interface SectionService {

    @RequestMapping(value = "/common/section/getSection", method = RequestMethod.GET)
    Response<List<Section>> getSection();
}
