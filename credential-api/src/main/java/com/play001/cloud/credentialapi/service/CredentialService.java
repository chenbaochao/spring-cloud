package com.play001.cloud.credentialapi.service;


import com.play001.cloud.common.entity.User;
import com.play001.cloud.credentialapi.mapper.UserMapper;
import com.play001.cloud.common.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CredentialService {

    private Logger logger = LoggerFactory.getLogger(CredentialService.class);

    @Autowired
    private UserMapper userMapper;
    /**
     * 获取身份令牌
     */
    public String getCredential(String key, String password, Long expiryDate) throws Exception {
        expiryDate = expiryDate==null?1000*60*60*24:expiryDate;//默认身份令牌有效期为一天
        User user = userMapper.findByUsername(key);
            if(user != null && user.getPassword().equals(password)){
                String jwt = JwtUtil.createJwt(user.getId(), key, System.currentTimeMillis()+expiryDate);
                logger.info("用户:"+user.getUsername()+"获取令牌成功, Jwt=" + jwt);
                return jwt;
            }else{
                throw new Exception("用户名或密码错误");
            }

    }

}
