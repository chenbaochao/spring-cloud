package com.play001.cloud.common.api.service;


import com.play001.cloud.common.entity.Advert;
import com.play001.cloud.common.api.mapper.AdvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各种广告
 */
@Service
public class AdvertService {

    /**
     * advert_category_id
     * 1.首页-轮播广告
     * 2.首页-轮播下方
     * 3.首页-分栏广告
     * 4.首页-明星产品
     */

    @Autowired
    private AdvertMapper advertMapper;

    /**
     * 获取首页图片轮播数据
     */
    public List<Advert> getSliderAdvert() {
        return advertMapper.getAdvertsByCategoryId(1, 8);
    }

    /**
     * 轮播下面的广告
     */
    public List<Advert> getUnderSliderAdvert() {
        return advertMapper.getAdvertsByCategoryId(2, 3);
    }
    /**
     * 首页明显产品
     */
    public List<Advert> getStartAdvert() {
        return advertMapper.getAdvertsByCategoryId(4, 99);
    }
}
