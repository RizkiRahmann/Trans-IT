package com.pioneers.transit.dto.response;

import com.pioneers.transit.entity.ResultHotelClient;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseClient {
    private String id;
    private ResultHotelClient result;
}
