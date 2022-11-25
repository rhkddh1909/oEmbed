package com.example.oemded.provider.serviceimpl;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Service
public class ProviderServiceImpl {
    private RestTemplate restTemplate;

    @Autowired
    public ProviderServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public JSONArray getProviders(String reqUrl) {
        try {
            JSONArray providers = new JSONArray(Optional.ofNullable(restTemplate.getForObject("https://oembed.com/providers.json", String.class))
                        .orElseThrow());
            return providers;
        } catch (JSONException e) {
            return new JSONArray();
        }

    }
}
