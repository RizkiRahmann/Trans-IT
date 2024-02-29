package com.pioneers.transit.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DestinationRequest {
    private String name;
    private String description;
    private String location;
    private Integer price;
    private Long rating;
}
