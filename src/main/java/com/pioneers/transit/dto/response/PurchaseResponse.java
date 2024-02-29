package com.pioneers.transit.dto.response;

import com.pioneers.transit.entity.Log;
import com.pioneers.transit.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private String id;
    @CreatedDate
    private Timestamp purchaseDate;
    private User user;
    private List<Log> logs;
}
