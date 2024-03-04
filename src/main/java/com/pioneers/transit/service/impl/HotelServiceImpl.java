package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.HotelRequest;
import com.pioneers.transit.dto.response.DestinationResponse;
import com.pioneers.transit.dto.response.HotelResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.entity.Hotel;
import com.pioneers.transit.repository.HotelRepository;
import com.pioneers.transit.service.HotelService;
import com.pioneers.transit.service.ValidationService;
import com.pioneers.transit.specification.destination.DestinationSpecification;
import com.pioneers.transit.specification.hotel.HotelSearchDTO;
import com.pioneers.transit.specification.hotel.HotelSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final ValidationService validationService;

/*@Override
    public HotelResponse create(HotelRequest request) {
        validationService.validate(request);
        Hotel responseHotel = Hotel.builder()
                .id(request.getId())
                .name(request.getName())
                .hotel_key(request.getHotel_key())
                .build();
        hotelRepository.save(responseHotel);
        return toHotelResponse(responseHotel);
    }*/


    /*@Override
    public PageResponseWrapper<HotelResponse> getAll(Pageable pageable, HotelSearchDTO hotelSearchDTO) {
        Specification<Hotel> specification = HotelSpecification.getSpecification(hotelSearchDTO);
        Page<Hotel> hotels = hotelRepository.findAll(specification,pageable);
        List<HotelResponse> hotelResponses = hotels.getContent().stream()
                .map(HotelServiceImpl::toHotelResponse).toList();
        PageImpl<HotelResponse> responses = new PageImpl<>(hotelResponses, pageable, hotels.getTotalElements());
        return new PageResponseWrapper<>(responses);

    }*/

    /*@Override
    public HotelResponse getById(String id) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(null);
        return toHotelResponse(hotel);
    }*/

/*@Override
    public HotelResponse update(HotelRequest request) {
        validationService.validate(request);
        Hotel hotel = hotelRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id hotel NOT FOUND"));
        hotel.setId(request.getId());
        hotel.setName(request.getName());
        hotel.setHotel_key(request.getHotel_key());
        return create(request);
    }*/


 /*@Override
    public void delete(String id) {
        hotelRepository.deleteById(id);
    }*/



    /*private static HotelResponse toHotelResponse(Hotel hotel){
        return HotelResponse.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .hotel_key(hotel.getHotel_key())
                .build();
    }*/
}
