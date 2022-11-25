package com.example.oemded.oEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto implements Serializable {
    private String STATUS;
    private String RSLT_MSG;
    private Map<String,Object> RSLT_DATA;
}
