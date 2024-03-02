package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class DestinationRequest {
    @Size(max = 100)
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @NotNull
    @Min(value = 100000)
    private Integer price;
    @NotNull
    private Long rating;
}
