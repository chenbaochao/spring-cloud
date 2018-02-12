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

    public ResponseEntity<UserAddress> findById(Long id, String userJwt) {
        ResponseEntity<UserAddress> responseEntity = new ResponseEntity<>();
        if(id == null){
            return responseEntity.setErrMsg("参数错误");
        }
        try {
            UserCredential userCredential = JwtUtil.getCredentialByJwt(userJwt);
            responseEntity.setMessage(userAddressMapper.findById(id, userCredential.getUserId()));
        } catch (IOException e) {
            e.printStackTrace();
            responseEntity.setErrMsg("内部错误");
        }

        return responseEntity;

    }
}
