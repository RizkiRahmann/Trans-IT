package com.pioneers.transit.controller;

import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.service.DestinationService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.DESTINATION)
public class DestinationController {
    private DestinationService destinationService;
    private BuildResponse buildResponse;

//    @PostMapping
//    public ResponseEntity<?>
}
