package com.pioneers.transit.controller;

import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.service.UserService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.USER)
public class UserController {
    private UserService userService;
    private BuildResponse buildResponse;

}
