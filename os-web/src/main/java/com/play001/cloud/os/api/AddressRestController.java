package com.play001.cloud.os.api;

import com.play001.cloud.os.service.UserAddressService;
import com.play001.cloud.support.entity.ResponseEntity;
import com.play001.cloud.support.entity.user.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/address")
public class AddressRestController {

    @Autowired
    private UserAddressService userAddressService;

    //修改
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Integer> add(UserAddress userAddress, @CookieValue("userJwt")String userJwt)  {
        return userAddressService.add(userAddress, userJwt);
    }
    //增加
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(UserAddress userAddress, @CookieValue("userJwt")String userJwt){
        return userAddressService.update(userAddress, userJwt);
    }
    //删除
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Integer> delete(Long id, @CookieValue("userJwt")String userJwt){
        return userAddressService.delete(id, userJwt);
    }
}
