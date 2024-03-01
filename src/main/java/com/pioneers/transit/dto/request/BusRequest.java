package com.pioneers.transit.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {
    private String name;
    private Integer chair;
    private Integer price;
}
