package com.pioneers.transit.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Location {
    private String id;
    private Long latitude;
    private Long longitude;
}
