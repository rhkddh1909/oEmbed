package com.example.oemded.provider.serviceimpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ProviderServiceImpl {
    private RestTemplate restTemplate;

    @Autowired
    public ProviderServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public JSONObject getProviders(String reqUrl) {
        String strRes = restTemplate.getForObject("https://oembed.com/providers.json",String.class);
        JSONObject response = null;
        try {
            response = new JSONObject(strRes);
        } catch (JSONException e) {

        }
        return response;
    }
}
