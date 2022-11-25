package com.example.oemded.oEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endpoint implements Serializable {
    private List<String> schemes = new ArrayList<>();
    private String url;
    private String discovery;
    private List<String> formats = new ArrayList<>();
}
