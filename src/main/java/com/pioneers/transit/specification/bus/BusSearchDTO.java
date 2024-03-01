package com.pioneers.transit.specification.bus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BusSearchDTO {
    private String busName;
    private Integer busChair;
    private Integer busPrice;
}
