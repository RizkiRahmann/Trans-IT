package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.repository.UserRepository;
import com.pioneers.transit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public UserResponse create(UserRequest request) {
        UserCredential userCredentialId = userCredentialRepository.findById(request.getUserCredentiall().getId())
                .orElseThrow(null);

        User user = User.builder()
                .username(request.getUsername())
                .name(request.getName())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .userCredentiall(userCredentialId)
                .build();
        userRepository.save(user);
        return toUserResponse(user);
    }

    @Override
    public PageResponseWrapper<UserResponse> getAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> list = users.getContent().stream().map(UserServiceImpl::toUserResponse).toList();
        PageImpl<UserResponse> userResponses = new PageImpl<>(list, pageable, users.getTotalElements());
        return new PageResponseWrapper<>(userResponses);
    }

    @Override
    public UserResponse getById(String id) {
        User user = userRepository.findById(id).orElseThrow(null);
        return toUserResponse(user);
    }

    @Override
    public UserResponse update(UserRequest request) {
        return create(request);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
    private static UserResponse toUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .userCredentiall(user.getUserCredentiall())
                .build();
    }
}
