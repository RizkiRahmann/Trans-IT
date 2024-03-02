package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.DestinationRequest;
import com.pioneers.transit.dto.response.DestinationResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.repository.DestinationRepository;
import com.pioneers.transit.service.DestinationService;
import com.pioneers.transit.service.ValidationService;
import com.pioneers.transit.specification.destination.DestinationSearchDTO;
import com.pioneers.transit.specification.destination.DestinationSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private final DestinationRepository destinationRepository;
    private final ValidationService validationService;

    @Override
    public DestinationResponse create(DestinationRequest request) {
        validationService.validate(request);
        Destination responseDestination = Destination.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .location(request.getLocation())
                .rating(request.getRating())
                .build();
        destinationRepository.save(responseDestination);
        return toDestinationResponse(responseDestination);
    }

    @Override
    public PageResponseWrapper<DestinationResponse> getAll(Pageable pageable, DestinationSearchDTO destinationSearchDTO) {
        Specification<Destination> specification = DestinationSpecification.getSpecification(destinationSearchDTO);
        Page<Destination> destinations = destinationRepository.findAll(specification,pageable);
        List<DestinationResponse> destinationResponses = destinations.getContent().stream()
                .map(DestinationServiceImpl::toDestinationResponse).toList();
        PageImpl<DestinationResponse> responses = new PageImpl<>(destinationResponses, pageable, destinations.getTotalElements());
        return new PageResponseWrapper<>(responses);
    }

    @Override
    public DestinationResponse getById(String id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(null);
        return toDestinationResponse(destination);
    }

    @Override
    public DestinationResponse update(DestinationRequest request) {
        validationService.validate(request);
        Destination destination = destinationRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id destination NOT FOUND"));
        destination.setName(request.getName());
        destination.setDescription(request.getDescription());
        destination.setLocation(request.getLocation());
        destination.setPrice(request.getPrice());
        destination.setRating(request.getRating());
        return create(request);
    }

    @Override
    public void delete(String id) {
        destinationRepository.deleteById(id);
    }

    private static DestinationResponse toDestinationResponse(Destination destination){
        return DestinationResponse.builder()
                .id(destination.getId())
                .name(destination.getName())
                .description(destination.getDescription())
                .price(destination.getPrice())
                .location(destination.getLocation())
                .rating(destination.getRating())
                .build();
    }

}
