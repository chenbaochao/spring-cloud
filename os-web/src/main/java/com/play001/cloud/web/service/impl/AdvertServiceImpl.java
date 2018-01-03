package com.play001.cloud.web.service.impl;

import com.play001.cloud.common.entity.Advert;
import com.play001.cloud.common.entity.IException;
import com.play001.cloud.common.entity.Response;
import com.play001.cloud.web.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertServiceImpl {
    @Autowired
    private AdvertService advertService;


    /**
     * 首页轮播
     */
    public List<Advert> getSliderAdvert() throws IException {
        Response<List<Advert>> response = advertService.getSliderAdvert();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }

    /**
     * 首页轮播下方广告
     */
    public List<Advert> getUnderSliderAdvert() throws IException {
        Response<List<Advert>> response = advertService.getUnderSliderAdvert();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }

    /**
     * 首页明星产品
     */
    public List<Advert> getStartAdvert() throws IException {
        Response<List<Advert>> response = advertService.getStartAdvert();
        if(Response.ERROR.equals(response.getStatus())) throw new IException(response.getErrMsg());
        return response.getMessage();
    }
}
