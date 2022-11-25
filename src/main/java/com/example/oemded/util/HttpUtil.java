package com.example.oemded.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
public class HttpUtil {

    public static HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity entity = new HttpEntity(headers);
        System.out.println(entity.getHeaders());
        return entity;
    }

    public static String getQueryUriString(String url){
        return UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("url", "{url}")
                .queryParam("format", "{format}")
                .encode()
                .toUriString();
    }
}
