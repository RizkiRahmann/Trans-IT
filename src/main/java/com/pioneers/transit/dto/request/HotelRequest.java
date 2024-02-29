package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HotelRequest {
    private String name;
    private String type;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date availableFrom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date availableTo;
    private boolean status;
}
