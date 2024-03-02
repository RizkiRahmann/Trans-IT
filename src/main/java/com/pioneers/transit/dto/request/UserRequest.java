package com.pioneers.transit.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pioneers.transit.entity.UserCredential;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @JsonIgnore
    @NotBlank
    @Size(max = 100)
    private String id;
    @NotBlank
    @Size(min = 8)
    private String username;
    @NotBlank
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @NotBlank
    private String address;
    @NotBlank
    @Size(min = 12,max = 13)
    private String phoneNumber;
    @NotBlank
    private UserCredential userCredential;
}
