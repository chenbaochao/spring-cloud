package com.play001.cloud.credentialapi.controller;



import com.play001.cloud.common.entity.Response;
import com.play001.cloud.credentialapi.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CredentialEndPoint {

    @Autowired
    private CredentialService credentialService;

    /**
     * 登陆成功,返回令牌
     * @param key 用户名or邮箱or电话号码
     * @param password 密码
     * @param expiryDate 令牌有效时长
     * @return
     */
    @RequestMapping(value = "/getCredential", method = RequestMethod.GET)
    public Response<String> login(String key, String password, Long expiryDate){
        Response<String> response = new Response();
        try {
            response.setStatus(Response.SUCCESS);
            response.setMessage(credentialService.getCredential(key, password, expiryDate));
        }catch (Exception e){
            response.setStatus(Response.ERROR);
            response.setErrMsg(e.getMessage());
        }
        return response;
    }
}
