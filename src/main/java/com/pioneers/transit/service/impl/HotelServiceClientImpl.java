package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.HotelRequest;
import com.pioneers.transit.dto.response.HotelResponse;
import com.pioneers.transit.dto.response.HotelResponseClient;
import com.pioneers.transit.entity.Hotel;
import com.pioneers.transit.repository.HotelRepository;
import com.pioneers.transit.service.HotelServiceClient;
import com.pioneers.transit.service.ValidationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

//import static com.pioneers.transit.service.impl.HotelServiceImpl.toHotelResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelServiceClientImpl implements HotelServiceClient {
    private final RestTemplate restTemplate;

    private final HotelRepository hotelRepository;
    private final ValidationService validationService;
    @Override
    public HotelResponseClient get(String chkIn, String chkOut, String hotelKey) {
        ResponseEntity<HotelResponseClient> response = restTemplate.getForEntity(
                "https://data.xotelo.com/api/rates?chk_in="+chkIn+"&chk_out="+chkOut+"&hotel_key="+hotelKey,
                HotelResponseClient.class
        );
        response.getBody().setId(UUID.randomUUID().toString());
        return response.getBody();
    }

    @Override
    public HotelResponse create(HotelRequest request) {
        validationService.validate(request);
        Hotel responseHotel = Hotel.builder()
                .id(request.getId())
                .name(request.getName())
                .hotel_key(request.getHotel_key())
                .build();
        hotelRepository.save(responseHotel);
        return toHotelResponse(responseHotel);
    }


    @Override
    public HotelResponse update(HotelRequest request) {
        validationService.validate(request);
        Hotel hotel = hotelRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id hotel NOT FOUND"));
        hotel.setId(request.getId());
        hotel.setName(request.getName());
        hotel.setHotel_key(request.getHotel_key());
        return create(request);
    }

    @Override
    public void delete(String id) {
        hotelRepository.deleteById(id);
    }


    private static HotelResponse toHotelResponse(Hotel hotel){
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .hotel_key(hotel.getHotel_key())
                .build();
    }
}
