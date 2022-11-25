package com.example.oemded.oEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endpoint implements Serializable {
    private List<String> schemes;
    private String url;
    private String discovery;
    private List<String> formats;
}
