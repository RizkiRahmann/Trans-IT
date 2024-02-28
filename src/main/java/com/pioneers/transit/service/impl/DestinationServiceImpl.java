package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.DestinationRequest;
import com.pioneers.transit.dto.response.DestinationResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.repository.DestinationRepository;
import com.pioneers.transit.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {
    private DestinationRepository destinationRepository;

    @Override
    public DestinationResponse create(DestinationRequest request) {
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
    public Page<DestinationResponse> getAll(Pageable pageable) {
        Page<Destination> destinations = destinationRepository.findAll(pageable);
        List<DestinationResponse> destinationResponses = destinations.getContent().stream()
                .map(DestinationServiceImpl::toDestinationResponse).toList();
        return new PageImpl<>(destinationResponses,pageable,destinations.getTotalElements());
    }

    @Override
    public DestinationResponse getById(String id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(null);
        return toDestinationResponse(destination);
    }

    @Override
    public DestinationResponse update(DestinationRequest request) {
        return create(request);
    }

    @Override
    public void delete(String id) {
        destinationRepository.deleteById(id);
    }

    private static DestinationResponse toDestinationResponse(Destination destination){
        return DestinationResponse.builder()
                .name(destination.getName())
                .description(destination.getDescription())
                .price(destination.getPrice())
                .location(destination.getLocation())
                .rating(destination.getRating())
                .build();
    }

}
