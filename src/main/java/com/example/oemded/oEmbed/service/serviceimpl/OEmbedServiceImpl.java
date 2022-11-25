package com.example.oemded.oEmbed.service.serviceimpl;

import com.example.oemded.oEmbed.dto.Provider;
import com.example.oemded.oEmbed.dto.Providers;
import com.example.oemded.util.HttpUtil;
import com.example.oemded.util.ProviderUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OEmbedServiceImpl {
    private RestTemplate restTemplate;

    @Autowired
    public OEmbedServiceImpl(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }
    public JSONObject getOEbed(String strUrl) throws JSONException {
        Providers providers = this.getProviders();
        String getOEmbedUrl = HttpUtil.getQueryUriString(ProviderUtil.getProviderOEmbedUrl(strUrl,providers));
        HttpEntity entity = HttpUtil.getHttpEntity();
        Map<String, String> param = new HashMap<>();
        param.put("url", strUrl);
        param.put("format", "json");

        String oEmbedJson = restTemplate.exchange(getOEmbedUrl, HttpMethod.GET, entity,String.class,param).getBody();

        return new JSONObject(oEmbedJson);
    }

    public Providers getProviders()
    {
        Provider[] providerArr = Optional.ofNullable(restTemplate.getForObject("https://oembed.com/providers.json", Provider[].class))
                .orElseThrow();
        return Providers.builder().providers(Arrays.stream(providerArr).toList()).build();
    }
}
