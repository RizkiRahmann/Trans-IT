package com.pioneers.transit.controller;

import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.HotelResponseClient;
import com.pioneers.transit.service.HotelServiceClient;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.HOTEL)
public class HotelClientController {
    private final HotelServiceClient hotelServiceClient;
    private final BuildResponse buildResponse;

    @GetMapping
    public ResponseEntity<?> geHotel(
            @RequestParam(name = "chk_in") String chkIn,
            @RequestParam(name = "chk_out") String chkOut,
            @RequestParam(name = "hotel_key") String hotelKey
    ){
        HotelResponseClient serviceHotel = hotelServiceClient.get(chkIn,chkOut,hotelKey);
        ControllerResponse<HotelResponseClient> response = buildResponse.response(
                serviceHotel,
                HttpStatus.OK.getReasonPhrase(),
                "Hotel",
                "Succesfully Get List %s");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
