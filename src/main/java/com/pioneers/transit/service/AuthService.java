package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.AuthRequest;
import com.pioneers.transit.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(AuthRequest request);
    UserResponse registerAdmin(AuthRequest request);
    String login(AuthRequest request);
}
