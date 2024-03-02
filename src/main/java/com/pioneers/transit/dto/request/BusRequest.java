package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusRequest {
    @Size(max = 100)
    private String id;
    @NotBlank
    private String name;
    @NotNull
    @Min(value = 30)
    @Max(value = 30)
    private Integer chair;
    @NotNull
    @Min(value = 10000)
    private Integer price;
}
