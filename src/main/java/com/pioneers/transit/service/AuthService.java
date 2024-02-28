package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.AuthRequest;
import com.pioneers.transit.dto.response.UserCredentialResponse;

public interface AuthService {
    UserCredentialResponse register(AuthRequest request);
    UserCredentialResponse registerAdmin(AuthRequest request);
    String login(AuthRequest request);
}
