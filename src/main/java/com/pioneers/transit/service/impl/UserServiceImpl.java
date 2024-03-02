package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.UserRequest;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.repository.UserRepository;
import com.pioneers.transit.service.UserService;
import com.pioneers.transit.service.ValidationService;
import com.pioneers.transit.specification.user.UserSearchDTO;
import com.pioneers.transit.specification.user.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;
    private final ValidationService validationService;

    @Override
    public UserResponse create(UserRequest request) {
        validationService.validate(request);

        UserCredential userCredentialId = userCredentialRepository.findById(request.getUserCredential().getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ID UserCredential Not Found"));
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
    public PageResponseWrapper<UserResponse> getAll(Pageable pageable, UserSearchDTO userSearchDTO) {
        Specification<User> specification = UserSpecification.  getSpecification(userSearchDTO);
        Page<User> users = userRepository.findAll(specification, pageable);
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
        validationService.validate(request);
        User user = userRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id User NOT FOUND"));

        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setBirthDate(request.getBirthDate());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);

        return toUserResponse(user);
    }

    @Override
    public void deleteById(String id) {
        User user = userRepository.findById(id).orElseThrow(null);
        userRepository.delete(user);
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
