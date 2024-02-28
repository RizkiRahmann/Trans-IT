package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.service.UserCredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserCredentialServiceImpl implements UserCredentialService {

    private final UserCredentialRepository userCredentialRepository;

    // verifikasi userId di dalam token
    @Override
    public UserCredential loadByUserId(String userId) {
        return userCredentialRepository.findById(userId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }

    // Authentikasi spring security -> login
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userCredentialRepository.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }
}
