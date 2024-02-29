package com.pioneers.transit.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogResponse {
    private String id;
    private Integer ticket_quantity;
    private Integer price;
    private String purchase_id;
    private String destination_id;
    //kurang attribute "hotel_id"
    private String bus_id;
}
