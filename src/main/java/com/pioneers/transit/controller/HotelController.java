package com.pioneers.transit.controller;

import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.entity.Hotel;
import com.pioneers.transit.service.HotelService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.HOTEL)
public class HotelController {
    private final HotelService hotelService;
    private final BuildResponse buildResponse;

    @GetMapping
    public ResponseEntity<?> getListPhoto(){
        List<Hotel> hotelResponseList = hotelService.getAll();
        ControllerResponse<List<Hotel>> response = buildResponse.response(
                hotelResponseList,
                HttpStatus.OK.getReasonPhrase(),
                "Hotel",
                "Succesfully Get List %s");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
