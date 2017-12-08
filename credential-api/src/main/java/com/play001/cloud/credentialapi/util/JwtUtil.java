package com.play001.cloud.credentialapi.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.play001.cloud.credentialapi.entity.Credential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Component
public class JwtUtil {

    @Value("${credential.secret}")
    private String secret;
    private final String header = "{ \"alg\": \"HS256\",\"typ\": \"JWT\"}";

    public String createJwt(Integer userId, Long expiryDate) throws UnsupportedEncodingException {
        Credential credential = new Credential(userId, expiryDate);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer(header+"."+credential.toJson())
                .sign(algorithm);

    }
}
