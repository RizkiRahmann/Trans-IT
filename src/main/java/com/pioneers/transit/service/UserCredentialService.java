package com.pioneers.transit.service;

import com.pioneers.transit.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserCredentialService extends UserDetailsService {
    // Verifikasi userId yang ada di JWT
    UserCredential loadByUserId(String userId);
}
