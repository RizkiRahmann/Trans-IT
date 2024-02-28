package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.AuthRequest;
import com.pioneers.transit.dto.response.UserResponse;
import com.pioneers.transit.entity.Role;
import com.pioneers.transit.entity.UserCredential;
import com.pioneers.transit.repository.UserCredentialRepository;
import com.pioneers.transit.security.JwtUtils;
import com.pioneers.transit.service.AuthService;
import com.pioneers.transit.service.RoleService;
import com.pioneers.transit.utils.constant.ERole;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserCredentialRepository userCredentialRepository;
    private final RoleService roleService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostConstruct
    public void initSuperAdmin(){
        String email = "superadmin@gmail.com";
        String password = "12345";
        Optional<UserCredential> optionalUserCredential = userCredentialRepository.findByEmail(email);
        if (optionalUserCredential.isPresent()) return;

        Role roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);
        Role roleSuperAdmin = roleService.getOrSave(ERole.ROLE_SUPER_ADMIN);
        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);

        String hashPassword = passwordEncoder.encode(password);

        UserCredential userCredential = UserCredential.builder()
                .email(email)
                .password(hashPassword)
                .roles(List.of(roleSuperAdmin,roleAdmin,roleCustomer))
                .build();
        userCredentialRepository.saveAndFlush(userCredential);
    }

    @Override
    public UserResponse register(AuthRequest request) {
        // buat role
        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);

        // hash password agar password tidak terlihat
        String hashPassword = passwordEncoder.encode(request.getPassword());

        // simpan ke db
        UserCredential userCredential = UserCredential.builder()
                .email(request.getEmail())
                .password(hashPassword)
                .roles(List.of(roleCustomer))
                .build();
        userCredentialRepository.saveAndFlush(userCredential);

        return toUserReponse(userCredential);
    }

    @Override
    public UserResponse registerAdmin(AuthRequest request) {
        // buat role
        Role roleCustomer = roleService.getOrSave(ERole.ROLE_CUSTOMER);
        Role roleAdmin = roleService.getOrSave(ERole.ROLE_ADMIN);

        String hashPassword = passwordEncoder.encode(request.getPassword());

        // simpan ke db
        UserCredential userCredential = UserCredential.builder()
                .email(request.getEmail())
                .password(hashPassword)
                .roles(List.of(roleAdmin,roleCustomer))
                .build();
        userCredentialRepository.saveAndFlush(userCredential);

        return toUserReponse(userCredential);
    }

    private static UserResponse toUserReponse(UserCredential userCredential){
        List<String> roles = userCredential.getRoles().stream().map(role -> role.getRole().name()).toList();
        return UserResponse.builder()
                .email(userCredential.getEmail())
                .roles(roles)
                .build();
    }

    @Override
    public String login(AuthRequest request) {
        // Object untuk melakukan authenticate
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getEmail(), // ambil email -> untuk username
                request.getPassword()
        );
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // simpan sesi untuk mengakses resource tertentu
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserCredential userCredential = (UserCredential) authenticate.getPrincipal();
        return jwtUtils.generateToken(userCredential);
    }
}
