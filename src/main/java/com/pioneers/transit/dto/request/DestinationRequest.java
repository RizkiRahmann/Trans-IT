package com.pioneers.transit.dto.request;

import ch.qos.logback.core.boolex.EvaluationException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
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
    @Min(0)
    @Max(5)
    private Double rating;
}
