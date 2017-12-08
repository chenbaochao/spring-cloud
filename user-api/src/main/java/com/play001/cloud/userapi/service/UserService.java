package com.play001.cloud.userapi.service;


import com.play001.cloud.userapi.entity.Response;
import com.play001.cloud.userapi.entity.User;
import com.play001.cloud.userapi.mapper.UserMapper;
import com.play001.cloud.userapi.util.Captcha;
import com.play001.cloud.userapi.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate template;

    public void insert(User user, String registerCookie, String code) throws Exception {
        //验证码是否正确
        if(registerCookie == null) throw new Exception("验证码cookie为空");
        String realCode = template.opsForValue().get(registerCookie);
        if(realCode == null) throw new Exception("验证码已过期");
        if(!code.toLowerCase().equals(realCode.toLowerCase())) throw new Exception("验证码错误");

        if(userMapper.countByUserInfo(user) > 0) throw new Exception("用户名已存在");
        try {
            String nowTime = TimeUtil.getTime();
            user.setCreatedTime(nowTime);
            user.setUpdatedTime(nowTime);
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            userMapper.insert(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("未知错误,请重试");
        }
    }

    public  Response<byte[]> createCaptcha() throws IOException {
        String code = Captcha.randCaptchaCode();
        String uuid = UUID.randomUUID().toString();
        //将验证码保存进redis,并设置验证码的有效期为10分钟
        template.opsForValue().append(uuid, code);
        template.expire(uuid, 60*10, TimeUnit.SECONDS);

        BufferedImage bi = Captcha.createCaptchaImg(code);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bi, "JPG", os);
        byte []imageByte = os.toByteArray();//图片验证码字节长度不定
        os.flush();
        os.close();
        byte []cookieByte = uuid.getBytes();//验证码的cookie长度为36,
        //将图片数据和验证码吗cookie整合到一起作为response里的message传递
        byte []data = new byte[36+imageByte.length];
        for(int i = 0;i < imageByte.length;i++){
            data[i] = imageByte[i];
        }
        for(int i= imageByte.length;i < data.length; i++){
            data[i] = cookieByte[i-imageByte.length];
        }
        Response<byte[]> response = new Response<>(Response.SUCCESS);
        response.setMessage(data);

        return response;
    }
}
