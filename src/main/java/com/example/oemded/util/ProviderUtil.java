package com.example.oemded.util;

import com.example.oemded.oEmbed.dto.Providers;
public class ProviderUtil {

    public static String getProviderOEmbedUrl(String strUrl, Providers providers) {
        return providers.getProviders()
                .stream()
                .filter(provider -> provider.getEndpoints()
                        .stream()
                        .anyMatch(endpoint-> endpoint.getSchemes().stream()
                                .map(scheme->scheme.replace("*","(.*)"))
                                .anyMatch(strUrl::matches)
                        )
                )
                .findFirst()
                .map(provider -> provider.getEndpoints().get(0).getUrl())
                .orElseThrow();
    }
}
