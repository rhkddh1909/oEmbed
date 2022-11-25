package com.example.oemded.oEmbed.service.serviceimpl;

import com.example.oemded.oEmbed.dto.Providers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
@RestClientTest(OEmbedServiceImpl.class)
class OEmbedServiceImplTest {
    @Autowired
    private MockRestServiceServer mockServer;
    @Autowired
    private OEmbedServiceImpl oEmbedService;

    @Test
    public void getProvidersTest(){
        //Stubbing
        mockServer.expect(requestTo("https://oembed.com/providers.json"))
                .andRespond(withSuccess(new ClassPathResource("/providers.json",getClass()), MediaType.APPLICATION_JSON));
        //when
        Providers testRslt = oEmbedService.getProviders();

        //then
        assertThat(testRslt.getProviders().get(1).getProvider_url()).isEqualTo("https://www.youtube.com/");
    }

    @Test
    public void getOEmbedTest() throws JSONException {
        //Stubbing
        String strUrl = "https://www.youtube.com/watch?v=dBD54EZIrZo";

        //getOEmbed
        mockServer.expect(requestTo("https://oembed.com/providers.json"))
                .andRespond(withSuccess(new ClassPathResource("/providers.json",getClass()), MediaType.APPLICATION_JSON));
        mockServer.expect(requestTo("https://www.youtube.com/oembed?url=https://www.youtube.com/watch?v%3DdBD54EZIrZo&format=json"))
                .andRespond(withSuccess(new ClassPathResource("/youtube.json",getClass()), MediaType.APPLICATION_JSON));
        //when
        Map<String,Object> oEmbedJson = null;
        oEmbedJson = oEmbedService.getOEbed(strUrl);

        //then
            assertThat(oEmbedJson.get("type").toString()).isEqualTo("video");
    }
}