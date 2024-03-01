package com.pioneers.transit.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusResponse {
    private String id;
    private String name;
    private Integer chair;
    private Integer price;
}
