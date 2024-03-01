package com.pioneers.transit.specification.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DestinationSearchDTO {
    private String destinationName;
    private String destinationDescription;
    private String destinationLocation;
}
