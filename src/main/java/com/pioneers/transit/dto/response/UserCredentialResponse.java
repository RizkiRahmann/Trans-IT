package com.pioneers.transit.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialResponse {
    private String email;
    private List<String> roles;
}
