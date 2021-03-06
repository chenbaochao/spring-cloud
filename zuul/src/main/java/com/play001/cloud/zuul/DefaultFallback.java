package com.play001.cloud.zuul;

import com.play001.cloud.zuul.entity.ResponseEntity;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 网关请求微服务失败时,设置默认返回值
 */

public class DefaultFallback implements ZuulFallbackProvider {

    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }

            /**
             * 设置返回默认信息
             */
            @Override
            public InputStream getBody() throws IOException {
                ResponseEntity<String> responseEntity = new ResponseEntity<>();
                responseEntity.setStatus(ResponseEntity.ERROR);
                responseEntity.setErrMsg("请求失败");
                String str = responseEntity.toJson();
                System.out.println(str);
                return new ByteArrayInputStream(str.getBytes());
            }

            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }
        };
    }
}
