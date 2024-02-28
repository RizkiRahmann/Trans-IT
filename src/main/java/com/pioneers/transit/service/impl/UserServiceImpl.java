package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserCredentialRepository repository;

    @Override
    public UserCredential loadByUserId(String userId){
        return repository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }

    //username
    @Override
    public UserCredential loadByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }

    //email
    @Override
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException{
            return repository.findByEmail(email)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized"));
    }


}
