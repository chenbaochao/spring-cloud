package com.play001.cloud.os.service;


import com.play001.cloud.os.mapper.UserAddressMapper;
import com.play001.cloud.support.entity.IException;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    public List<UserAddress> getAll(String userJwt) throws IException {
        ResponseEntity<List<UserAddress>> responseEntity = userAddressMapper.getAll(userJwt);
        if(responseEntity.getStatus().equals(ResponseEntity.ERROR)){
            throw new IException(responseEntity.getErrMsg());
        }
        return responseEntity.getMessage();
    }
}
