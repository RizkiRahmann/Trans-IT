package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.BusRequest;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.repository.BusRepository;
import com.pioneers.transit.service.BusService;
import com.pioneers.transit.service.ValidationService;
import com.pioneers.transit.specification.bus.BusSearchDTO;
import com.pioneers.transit.specification.bus.BusSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;
    private final ValidationService validationService;

    @Override
    public BusResponse create(BusRequest request) {
        validationService.validate(request);
        Bus bus = buildBus(request);
        busRepository.save(bus);
        return toResponse(bus);
    }

    @Override
    public PageResponseWrapper<BusResponse> getAll(Pageable pageable, BusSearchDTO busSearchDTO) {
        Specification<Bus> specification = BusSpecification.getSpecification(busSearchDTO);
        Page<Bus> busPage =busRepository.findAll(specification,pageable);
        List<BusResponse> responseList = busPage.getContent().stream()
                .map(BusServiceImpl::toResponse).toList();
        PageImpl<BusResponse> responses = new PageImpl<>(responseList,pageable,busPage.getTotalElements());
        return new PageResponseWrapper<>(responses);
    }

    @Override
    public BusResponse getById(String id) {
        Bus bus = busRepository.findById(id).orElseThrow(null);
        return toResponse(bus);
    }

    @Override
    public BusResponse update(BusRequest request) {
        validationService.validate(request);
        Bus bus = busRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id User NOT FOUND"));
        bus.setChair(request.getChair());
        bus.setName(request.getName());
        bus.setPrice(request.getPrice());
        busRepository.save(bus);
        return toResponse(bus);
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
