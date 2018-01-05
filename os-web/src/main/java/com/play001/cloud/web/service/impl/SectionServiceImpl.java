package com.play001.cloud.web.service.impl;

import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.common.entity.Section;
import com.play001.cloud.web.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionServiceImpl {

    @Autowired
    private SectionService sectionService;

    /**
     * 首页的产品显示
     */
    public List<Section> getSection() throws IException {
        Response<List<Section>> response = sectionService.getSection();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }
}
