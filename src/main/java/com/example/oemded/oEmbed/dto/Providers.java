package com.example.oemded.oEmbed.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Providers implements Serializable {
    List<Provider> providers;
}
