package com.play001.cloud.os.mapper;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.Section;
import com.play001.cloud.os.service.fallback.DefaultFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "ZUUL", fallback = SectionMapper.SectionFallback.class)
public interface SectionMapper {

    @RequestMapping(value = "/common/section/getIndexSections", method = RequestMethod.GET)
    ResponseEntity<List<Section>> getIndexSections();

    @RequestMapping(value = "/common/section/getHeaderSections", method = RequestMethod.GET)
    ResponseEntity<List<Section>> getHeaderSections();
    @Component
    static class SectionFallback implements SectionMapper{
        private final ResponseEntity<List<Section>> responseEntity = new ResponseEntity<List<Section>>().setErrMsg("网络繁忙");
        @Override
        public ResponseEntity<List<Section>> getIndexSections() {
            return responseEntity;
        }

        @Override
        public ResponseEntity<List<Section>> getHeaderSections() {
            return responseEntity;
        }
    }
}
