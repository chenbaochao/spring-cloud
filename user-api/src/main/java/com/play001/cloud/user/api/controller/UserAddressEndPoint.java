package com.play001.cloud.user.api.controller;

import com.netflix.ribbon.proxy.annotation.Http;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.UserAddress;
import com.play001.cloud.support.interceptor.UserPermissionVerify;
import com.play001.cloud.user.api.service.UserAddressService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @UserPermissionVerify
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@RequestBody UserAddress userAddress, @RequestHeader("userJwt")String userJwt) throws IOException {
        return userAddressService.update(userAddress, userJwt);
    }
    @UserPermissionVerify
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(@RequestHeader("userJwt")String userJwt,@RequestBody UserAddress userAddress) throws IOException {
        return userAddressService.add(userAddress, userJwt);
    }
    @UserPermissionVerify
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(@RequestHeader("userJwt")String userJwt, Long id) throws IOException {
        return userAddressService.delete(id, userJwt);
    }

}
