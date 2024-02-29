package com.pioneers.transit.dto.response;

import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.Purchase;
import lombok.*;

import javax.print.attribute.standard.Destination;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogResponse {
    private String id;
    private Integer ticketQuantity;
    private Integer price;
    private Purchase purchase;
//    private Destination destination;
    private Bus bus;
}
