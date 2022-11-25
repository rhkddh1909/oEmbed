package com.example.oemded.util;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HttpUtilTest {
    @Test
    public void getHttpEntityTest(){
        //Stubbing
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity stubEntity = new HttpEntity(headers);
        //when
        HttpEntity entity = HttpUtil.getHttpEntity();
        //then
        assertThat(entity).isEqualTo(stubEntity);
    }

    @Test
    public void getUriStringTest(){
        //Stubbing
        String providerUrl = "https://www.youtube.com/oembed";
        //when
        String strUri = HttpUtil.getQueryUriString(providerUrl);
        //then
        assertThat(strUri).isEqualTo("https://www.youtube.com/oembed?url={url}&format={format}");
    }
}