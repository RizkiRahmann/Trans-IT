package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.HotelRequestClient;
import com.pioneers.transit.dto.response.HotelResponseClient;
import com.pioneers.transit.service.HotelServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceClientImpl implements HotelServiceClient {
    private final RestTemplate restTemplate;
    @Override
    public HotelResponseClient get(String chkIn, String chkOut, String hotelKey) {
        ResponseEntity<HotelResponseClient> response = restTemplate.getForEntity(
                "https://data.xotelo.com/api/rates?chk_in="+chkIn+"&chk_out="+chkOut+"&hotel_key="+hotelKey,
                HotelResponseClient.class
        );
        response.getBody().setId(UUID.randomUUID().toString());
        return response.getBody();
    }

    private static HotelResponseClient toHotelClientResponse(HotelRequestClient requestClient){
        return HotelResponseClient.builder()
                .result(requestClient.getResult())
                .build();
    }
}
