package com.example.oemded.oEmbed.service;

import com.example.oemded.oEmbed.dto.Providers;
import org.json.JSONException;
import java.util.Map;

public interface OEmbedService {
    public Map<String,Object> getOEbed(String strUrl) throws JSONException;

    public Providers getProviders();
}
