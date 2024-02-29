package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserResponse create(UserRequest request);
    PageResponseWrapper<UserResponse> getAll(Pageable pageable);
    UserResponse getById(String id);
    UserResponse update(UserRequest request);
    void deleteById(String id);

}
