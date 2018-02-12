package com.play001.cloud.user.api.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.UserAddress;
import com.play001.cloud.support.interceptor.UserPermissionVerify;
import com.play001.cloud.user.api.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class UserAddressEndPoint {

    @Autowired
    private UserAddressService userAddressService;

    @UserPermissionVerify
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<UserAddress>> getAll(@RequestHeader("userJwt")String userJwt) throws IOException {
        return userAddressService.getAll(userJwt);
    }
    @UserPermissionVerify
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResponseEntity<UserAddress> findById(@RequestHeader("userJwt")String userJwt, Long id) throws IOException {
        return userAddressService.findById(id, userJwt);
    }
}
