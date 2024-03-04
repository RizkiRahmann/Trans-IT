package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.HotelRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.HotelResponse;
import com.pioneers.transit.dto.response.HotelResponseClient;
import com.pioneers.transit.service.HotelServiceClient;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.HOTEL)
public class HotelClientController {
    private final HotelServiceClient hotelServiceClient;
    private final BuildResponse buildResponse;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping
    public ResponseEntity<?> getHotel(
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping("/{hotelKey}")
    public ResponseEntity<?> getHotelKey( @PathVariable String hotelKey){
        HotelResponseClient serviceHotel = hotelServiceClient.getByHotelKey(hotelKey);
        ControllerResponse<HotelResponseClient> response = buildResponse.response(
                serviceHotel,
                HttpStatus.OK.getReasonPhrase(),
                "Hotel",
                "Succesfully Get List %s");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public ResponseEntity<?> createHotelToRepo(@RequestBody HotelRequest hotelRequest){
        HotelResponse hotelResponse = hotelServiceClient.create(hotelRequest);
        ControllerResponse<HotelResponse> response = buildResponse.response(
                hotelResponse,
                HttpStatus.OK.getReasonPhrase(),
                "Hotel",
                "Succesfully Get List %s");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateHotelToRepo(@RequestBody HotelRequest hotelRequest){
        HotelResponse hotelResponse = hotelServiceClient.update(hotelRequest);
        ControllerResponse<HotelResponse> response = buildResponse.response(
                hotelResponse,
                HttpStatus.OK.getReasonPhrase(),
                "Hotel",
                "Succesfully Get List %s");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
//    @DeleteMapping("{id}")
//    public ResponseEntity<?> updateHotelToRepo(@PathVariable String id){
//        hotelServiceClient.delete(id);
//        ControllerResponse<String> response = buildResponse.response(
//                "Hotel berhasil dihapus",
//                HttpStatus.OK.getReasonPhrase(),
//                "Hotel",
//                "Succesfully Get List %s");
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }
}
