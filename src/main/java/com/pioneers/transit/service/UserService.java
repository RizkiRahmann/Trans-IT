package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.dto.response.UserResponseImage;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.specification.user.UserSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    UserResponse create(UserRequest request);
    PageResponseWrapper<UserResponse> getAll(Pageable pageable, UserSearchDTO userSearchDTO);
    UserResponse getById(String id);
    UserResponse update(UserRequest request);
    UserResponseImage updateImage(String id, MultipartFile file) throws IOException;
    void deleteById(String id);

    byte[] getImage(String id);

}
