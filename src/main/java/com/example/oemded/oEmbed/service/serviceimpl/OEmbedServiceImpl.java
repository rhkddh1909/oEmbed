package com.example.oemded.oEmbed.service.serviceimpl;

import com.example.oemded.exception.OEmbedException;
import com.example.oemded.oEmbed.dto.Provider;
import com.example.oemded.oEmbed.dto.Providers;
import com.example.oemded.oEmbed.service.OEmbedService;
import com.example.oemded.util.HttpUtil;
import com.example.oemded.util.ProviderUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class OEmbedServiceImpl implements OEmbedService{
    private RestTemplate restTemplate;

    @Autowired
    public OEmbedServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public Map<String,Object> getOEbed(String strUrl) throws JSONException{
        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        log.info("Service.getOEbed=>{}",strUrl);
        Providers providers = this.getProviders();
        log.info("providers=>{}",providers);
        String getOEmbedUrl = HttpUtil.getQueryUriString(ProviderUtil.getProviderOEmbedUrl(strUrl,providers));
        log.info("getOEmbedUrl=>{}",getOEmbedUrl);
        HttpEntity entity = HttpUtil.getHttpEntity();
        Map<String, String> param = new HashMap<>();
        param.put("url", strUrl);
        param.put("format", "json");

        log.info("header=>{}",entity.getHeaders());
        log.info("body=>{}",entity.getBody());

        Map<String, Object> oEmbedMap = Optional.ofNullable(restTemplate.exchange(getOEmbedUrl, HttpMethod.GET, entity,Map.class,param).getBody())
                .orElseThrow(()->new OEmbedException("OEmbed api 호출에 실패했습니다."));
        log.info("oEmbedJson=>{}",oEmbedMap);
        JSONObject jo = new JSONObject(oEmbedMap);

        return oEmbedMap;
    }
    @Override
    public Providers getProviders() {
        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request,body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        HttpEntity entity = HttpUtil.getHttpEntity();
        log.info("header=>{}",entity.getHeaders());
        log.info("body=>{}",entity.getBody());
        Provider[] providerArr = Optional.ofNullable(restTemplate.exchange("https://oembed.com/providers.json",HttpMethod.GET, entity, Provider[].class).getBody())
                .orElseThrow(()->new OEmbedException("Providers api 호출에 실패했습니다."));
        log.info("providerArr.length=>{}",providerArr.length);
        log.info("providerList=>{}", Arrays.stream(providerArr).toList());

        return Providers.builder().providers(Arrays.stream(providerArr).toList()).build();
    }
}
