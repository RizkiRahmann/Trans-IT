package com.pioneers.transit.controller;

import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.service.HotelService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.HOTEL)
public class HotelController {
    private final HotelService hotelService;
    private final BuildResponse buildResponse;
    private final String entity = "Hotel";
}
