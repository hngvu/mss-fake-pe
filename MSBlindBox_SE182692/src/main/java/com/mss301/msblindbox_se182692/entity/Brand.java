package com.mss301.msblindbox_se182692.entity;

import lombok.Builder;

@Builder
public record Brand(
        int id,
        String name,
        String countryOfOrigin
) {
}
