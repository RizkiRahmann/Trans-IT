package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.response.HotelResponse;
import com.pioneers.transit.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final RestTemplate restTemplate;
    private String baseUrl;

    @Override
    public List<HotelResponse> getHotel() {
        return null;
    }
}
