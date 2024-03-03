package com.pioneers.transit.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pioneers.transit.entity.Image;
import com.pioneers.transit.entity.UserCredential;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String id;
    private String username;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String address;
    private String phoneNumber;
    private UserCredential userCredentiall;
}
