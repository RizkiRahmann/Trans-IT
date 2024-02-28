package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.BusRequest;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.repository.BusRepository;
import com.pioneers.transit.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;

    @Override
    public BusResponse create(BusRequest request) {
        Bus bus = buildBus(request);
        busRepository.save(bus);
        return toResponse(bus);
    }

    @Override
    public PageResponseWrapper<BusResponse> getAll(Pageable pageable) {
        PageResponseWrapper<Bus> busPage = new PageResponseWrapper<>(busRepository.findAll(pageable));
        List<BusResponse> responseList = busPage.getData().stream()
                .map(BusServiceImpl::toResponse).toList();
        return null;
    }

    @Override
    public BusResponse getById(String id) {
        Bus bus = busRepository.findById(id).orElseThrow(null);
        return toResponse(bus);
    }

    @Override
    public BusResponse update(BusRequest request) {
        return create(request);
    }

    @Override
    public void delete(String id) {
        busRepository.deleteById(id);
    }
    private static Bus buildBus(BusRequest request){
        return Bus.builder()
                .name(request.getName())
                .chair(request.getChair())
                .price(request.getPrice())
                .build();
    }
    private static BusResponse toResponse(Bus bus){
        return BusResponse.builder()
                .id(bus.getId())
                .name(bus.getName())
                .chair(bus.getChair())
                .price(bus.getPrice())
                .build();
    }

}
