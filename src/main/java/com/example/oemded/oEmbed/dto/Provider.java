package com.example.oemded.oEmbed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider implements Serializable {
    private String provider_name;
    private String provider_url;
    private List<Endpoint> endpoints;
}
