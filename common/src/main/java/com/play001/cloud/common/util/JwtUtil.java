package com.play001.cloud.common.util;

import com.google.gson.Gson;
import com.play001.cloud.common.entity.Credential;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class JwtUtil {
    //加密字符串,密匙
    private static String secret = "xxx1aAxxx!!!";

    private static final String header = "{ \"alg\": \"HS256\",\"typ\": \"JWT\"}";

    public static String createJwt(Integer userId, String username, Long expiryDate) throws Exception {
        Credential credential = new Credential(userId, username, expiryDate);
        String base64Header = Base64.encodeBase64String(header.getBytes());
        String base64Payload =  Base64.encodeBase64String(credential.toJson().getBytes());
        String signature = HMACSHA256(base64Header,base64Payload,secret);
        return base64Header+"."+base64Payload+"."+signature;

    }

    /**
     * 验证口令是否正确已经是否过期
     * @param jwt 口令
     * @return 结果
     */
    public static  boolean verify(String jwt) {
        try {
            if(jwt == null || jwt.length() < 1) return false;
            String []arr = jwt.split(".");
            if(arr.length != 3) return false;
            String oldSignature = arr[2];
            String payload  = new String(new BASE64Decoder().decodeBuffer(arr[1]));
            Credential credential = new Gson().fromJson(payload, Credential.class);
            if(credential.getExpiryDate() < System.currentTimeMillis()) return false;
            String newSignature = HMACSHA256(arr[0],arr[1],secret);
            return oldSignature.equals(newSignature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    /**
     * 加密
     * @param header base64后的头部
     * @param payload base64后的用户信息
     * @param key 密匙
     * @return 加密后数据
     */
    private static String HMACSHA256(String header, String payload, String key) throws Exception {
        byte[] data = (header+payload).getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);
        return Base64.encodeBase64String(mac.doFinal(data));//加密后进行base64再加密
    }


}
