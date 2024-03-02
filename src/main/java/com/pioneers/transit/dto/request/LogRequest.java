package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.entity.Purchase;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogRequest {
    @Size(max = 100)
    private String id;
    @NotNull
    @Min(value = 1)
    private Integer ticketQuantity;
    @Min(value = 0)
    private Integer price;
    @NotBlank
    private String hotelKey;
    @NotBlank
    private String hotelUrl;
    private Purchase purchase;
    private Destination destination;
    private Bus bus;

}
