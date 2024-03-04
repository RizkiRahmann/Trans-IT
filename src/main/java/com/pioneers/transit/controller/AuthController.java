package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.AuthRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.UserCredentialResponse;
import com.pioneers.transit.service.AuthService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstStatus;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.AUTH)
@RequiredArgsConstructor
public class    AuthController {
    private final AuthService authService;
    private final BuildResponse buildResponse;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request){
        UserCredentialResponse userCredentialResponse = authService.register(request);
        ControllerResponse<UserCredentialResponse> response = buildResponse.response(userCredentialResponse, ConstStatus.STATUS_CREATE, "User", "Successfully Register New %s");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request){
        UserCredentialResponse userCredentialResponse = authService.registerAdmin(request);
        ControllerResponse<UserCredentialResponse> response = buildResponse.response(userCredentialResponse, ConstStatus.STATUS_CREATE, "Admin", "Successfully Register New %s");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        String token = authService.login(request);
        ControllerResponse<String> response = buildResponse.response(token, ConstStatus.STATUS_CREATE, "", "Successfully Login");
        return ResponseEntity.ok(response);
    }
}
