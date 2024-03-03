package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pioneers.transit.entity.Log;
import com.pioneers.transit.entity.Payment;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.utils.constant.EnumPayment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(max = 100)
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    private EnumPayment payment;
    private User user;
    private List<Log> logs;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date chkIn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date chkOut;
}
