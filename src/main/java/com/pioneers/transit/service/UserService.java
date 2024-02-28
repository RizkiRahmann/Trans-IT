package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.User;

public interface UserService {
    UserResponse create(UserRequest request);
}
