package com.pioneers.transit.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {
    private Integer ticket_quantity;
    private Integer price;
    private String purchase_id;
    private String destination_id;
    //kurang attribute "hotel_id"
    private String bus_id;
}
