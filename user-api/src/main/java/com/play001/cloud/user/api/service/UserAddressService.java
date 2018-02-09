package com.play001.cloud.user.api.service;

import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.User;
import com.play001.cloud.support.entity.user.UserAddress;
import com.play001.cloud.support.entity.user.UserCredential;
import com.play001.cloud.support.util.JwtUtil;
import com.play001.cloud.user.api.mapper.UserAddressMapper;
import com.play001.cloud.user.api.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    public ResponseEntity<List<UserAddress>> getAll(String userJwt) throws IOException {
        ResponseEntity<List<UserAddress>> responseEntity = new ResponseEntity<>();
        UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
        List<UserAddress> userAddresses = userAddressMapper.getAll(userCredential.getUserId());
        return responseEntity.setMessage(userAddresses);
    }
}
