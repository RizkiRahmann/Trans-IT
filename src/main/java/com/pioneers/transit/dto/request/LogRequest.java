package com.pioneers.transit.dto.request;

import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.entity.Purchase;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {
    private Integer ticketQuantity;
    private Integer price;
    private String hotelKey;
    private String hotelUrl;

    private Purchase purchase;
    private Destination destination;
    private Bus bus;

}
