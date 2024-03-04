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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.UUID;

//import static com.pioneers.transit.service.impl.HotelServiceImpl.toHotelResponse;

@Service
@RequiredArgsConstructor
public class HotelServiceClientImpl implements HotelServiceClient {
    private final RestTemplate restTemplate;
    private final HotelRepository hotelRepository;
    private final ValidationService validationService;

    @Value("${app.trans-it.base-url}")
    private String baseUrl;
    @Override
    public HotelResponseClient get(String chkIn, String chkOut, String hotelKey) {
        ResponseEntity<HotelResponseClient> response = restTemplate.getForEntity(
                baseUrl.formatted(chkIn,chkOut,hotelKey),
                HotelResponseClient.class
        );
        return response.getBody();
    }

    @Override
    public HotelResponseClient getByHotelKey(String hotelKey) {
        LocalDate tommorow = LocalDate.now().plusDays(1);
        LocalDate tommorowAgain = LocalDate.now().plusDays(2);
        ResponseEntity<HotelResponseClient> response = restTemplate.getForEntity(
                baseUrl.formatted(tommorow,tommorowAgain,hotelKey),
                HotelResponseClient.class
        );
        return response.getBody();
    }

    @Override
    public HotelResponse create(HotelRequest request) {
        validationService.validate(request);
        HotelResponseClient hotelResponseClient = getByHotelKey(request.getHotelKey());
        if (hotelResponseClient.getResult()==null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotel key not found");
        Hotel responseHotel = Hotel.builder()
                .name(request.getName())
                .hotelKey(request.getHotelKey())
                .build();
        hotelRepository.save(responseHotel);
        return toHotelResponse(responseHotel);
    }


    @Override
    public HotelResponse update(HotelRequest request) {
        validationService.validate(request);
        Hotel hotel = hotelRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id hotel NOT FOUND"));
        hotel.setName(request.getName());
        hotel.setHotelKey(request.getHotelKey());
        return create(request);
    }

//    @Override
//    public void delete(String id) {
//        hotelRepository.deleteById(id);
//    }


    private static HotelResponse toHotelResponse(Hotel hotel){
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .hotelKey(hotel.getHotelKey())
                .build();
    }
}
