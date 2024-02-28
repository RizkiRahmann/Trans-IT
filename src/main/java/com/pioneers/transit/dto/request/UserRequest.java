package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pioneers.transit.entity.UserCredential;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String address;
    private String phoneNumber;
    private UserCredential userCredentiall;
}
