package com.pioneers.transit.service;

import com.pioneers.transit.entity.UserCredential;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    UserCredential loadByUserId(String userId);

    //username
    UserCredential loadByUsername(String username) throws UsernameNotFoundException;

    //email
    UserDetails loadUserByUsername(String email)throws UsernameNotFoundException;
}
