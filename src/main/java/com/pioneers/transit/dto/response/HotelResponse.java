package com.pioneers.transit.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponse {
    private String id;
    private String name;
    private String hotel_key;
}
