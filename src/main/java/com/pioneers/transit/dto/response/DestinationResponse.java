package com.pioneers.transit.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationResponse {
    private String id;
    private String name;
    private String description;
    private String location;
    private Integer price;
    private Double rating;
}
