package com.pioneers.transit.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HotelResponse {
    private String name;
    private String type;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date availableFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date availableTo;
    private boolean status;
}
