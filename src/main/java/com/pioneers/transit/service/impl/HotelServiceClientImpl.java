package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.Hotel;
import com.pioneers.transit.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceImpl implements HotelService {
    private RestTemplate restTemplate;
    @Override
    public List<Hotel> getAll() {
        ResponseEntity<Hotel[]> response =
                restTemplate.getForEntity(
                        "https://jsonplaceholder.typicode.com/photos",
                        Hotel[].class
                );
        log.info("responseee " + response);
        return Arrays.asList(response.getBody());
    }
}
