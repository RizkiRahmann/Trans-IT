package com.pioneers.transit.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
