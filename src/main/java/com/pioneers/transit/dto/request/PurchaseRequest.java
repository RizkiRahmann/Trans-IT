package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pioneers.transit.entity.Log;
import com.pioneers.transit.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    private User user;
    private List<Log> logs;
}
