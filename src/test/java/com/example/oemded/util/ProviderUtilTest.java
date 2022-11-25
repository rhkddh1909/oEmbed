package com.example.oemded.util;

import com.example.oemded.oEmbed.dto.Providers;
import com.example.oemded.oEmbed.service.serviceimpl.OEmbedServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(OEmbedServiceImpl.class)
class ProviderUtilTest {
    @Autowired
    private MockRestServiceServer mockServer;
    @Autowired
    private OEmbedServiceImpl oEmbedService;

    @Test
    public void getProviderTest(){
        //Stubbing
        String strUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";
        mockServer.expect(requestTo("https://oembed.com/providers.json"))
                .andRespond(withSuccess(new ClassPathResource("/providers.json",getClass()), MediaType.APPLICATION_JSON));
        //when
        Providers testProviders = oEmbedService.getProviders();
        String providerOEmbedUrl = ProviderUtil.getProviderOEmbedUrl(strUrl,testProviders);
        //then
        assertThat(providerOEmbedUrl).isEqualTo("https://www.youtube.com/oembed");
    }
}