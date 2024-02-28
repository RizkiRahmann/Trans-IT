package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService  {
    UserCredential loadByUserId(String userId);

    UserResponse create(UserRequest request);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
