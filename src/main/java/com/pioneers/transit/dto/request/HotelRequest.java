package com.pioneers.transit.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String hotel_key;
}
