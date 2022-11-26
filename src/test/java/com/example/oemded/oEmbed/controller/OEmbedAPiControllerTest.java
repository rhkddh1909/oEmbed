package com.example.oemded.oEmbed.controller;

import com.example.oemded.oEmbed.dto.BaseDto;
import com.example.oemded.oEmbed.dto.OEmbedReq;
import com.example.oemded.oEmbed.service.OEmbedService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OEmbedAPiControllerTest {
    @Mock
    private OEmbedService mockOEmbedService;

    @InjectMocks
    private OEmbedAPiController mockOEmbedAPiController;

    @Test
    public void test_given_OEmbedSerivce_When_CallGetOEmbed_Then_Should_CallGetOEmbed() throws JSONException {
        //Stubbing
        OEmbedReq oEmbedReq = new OEmbedReq();
        oEmbedReq.setStrUrl("url");
        given(mockOEmbedService.getOEbed(anyString())).willReturn(new HashMap<String,Object>(){{put("title","핑크퐁");}});
        //when
        ResponseEntity<BaseDto> omebed = mockOEmbedAPiController.getOEmbed(oEmbedReq);
        //then
        then(mockOEmbedService).should().getOEbed(anyString());
        assertThat(omebed.getBody().getSTATUS()).isEqualTo("0000");
        assertThat(omebed.getBody().getRSLT_DATA().get("title")).isEqualTo("핑크퐁");
    }
}