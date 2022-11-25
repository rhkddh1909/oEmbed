package com.example.oemded.provider.serviceimpl;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(ProviderServiceImpl.class)
class ProviderServiceImplTest {
    @Autowired
    private MockRestServiceServer mockServer;
    @Autowired
    private ProviderServiceImpl providerService;
    @Test
    public void test_getProviders(){
        //Stubbing
        mockServer.expect(requestTo("https://oembed.com/providers.json"))
                .andRespond(withSuccess(new ClassPathResource("/providers.json",getClass()), MediaType.APPLICATION_JSON));
        //when
        JSONArray testRslt = providerService.getProviders("https://oembed.com/providers.json");

        //then
        assertThat(testRslt).isNull();
    }
}