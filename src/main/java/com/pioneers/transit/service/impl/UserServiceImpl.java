package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserResponse create(UserRequest request) {
        return null;
    }

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserResponse getById(String id) {
        return null;
    }

    @Override
    public UserResponse update(UserRequest request) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }
}
