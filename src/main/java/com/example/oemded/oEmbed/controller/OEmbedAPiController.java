package com.example.oemded.oEmbed.controller;

import com.example.oemded.code.OEMBED_CODE;
import com.example.oemded.oEmbed.dto.BaseDto;
import com.example.oemded.oEmbed.dto.OEmbedReq;
import com.example.oemded.oEmbed.service.OEmbedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OEmbedAPiController {
    private final OEmbedService oEmbedService;

    @PostMapping("/oEmbed")
    public ResponseEntity<BaseDto> getOEmbed(@RequestBody OEmbedReq reqUrl){
        log.info("reqUrl=>{}",reqUrl);
        try{
            Map<String, Object> oEmbed = oEmbedService.getOEbed(reqUrl.getStrUrl());
            log.info("oEmbedResult=>{}",oEmbed);

            return ResponseEntity.ok().body(BaseDto.builder()
                    .STATUS(OEMBED_CODE.STATUS.OK.CODE())
                    .RSLT_MSG("")
                    .RSLT_DATA(oEmbed)
                    .build());
        }
        catch(JSONException e){
            log.info("JSONException=>{]",e.getMessage());
            return ResponseEntity.ok().body(BaseDto.builder()
                    .STATUS(OEMBED_CODE.STATUS.ERROR.CODE())
                    .RSLT_MSG("JSON parsing 오류입니다.")
                    .build());
        }
        catch(Exception e){
            log.info("Exception={}", e.getMessage());
            return ResponseEntity.ok().body(BaseDto.builder()
                    .STATUS(OEMBED_CODE.STATUS.ERROR.CODE())
                    .RSLT_MSG(e.getMessage())
                    .build());
        }
    }
}
