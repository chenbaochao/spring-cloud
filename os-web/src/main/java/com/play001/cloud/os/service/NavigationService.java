package com.play001.cloud.os.service;


import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.NavigationBar;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.os.mapper.NavigationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NavigationService {

    @Autowired
    private NavigationMapper navigationMapper;

    /**
     * 首页-顶部 数据
     */
    public List<NavigationBar> getTopBarNavigationBars() throws IException {
        ResponseEntity<List<NavigationBar>> responseEntity = navigationMapper.getTopBarNavigationBars();
        if(Objects.equals(responseEntity.getStatus(), ResponseEntity.ERROR)){
            throw  new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }

    /**
     * 首页-轮播图下方六个小链接
     */
    public List<NavigationBar> getChannelNavigationBars() throws IException {
        ResponseEntity<List<NavigationBar>> responseEntity = navigationMapper.getChannelNavigationBars();
        if(Objects.equals(responseEntity.getStatus(), ResponseEntity.ERROR)){
            throw  new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }
}
