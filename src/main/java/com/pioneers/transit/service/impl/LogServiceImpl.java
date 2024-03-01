package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.response.LogResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.Destination;
import com.pioneers.transit.entity.Log;
import com.pioneers.transit.repository.BusRepository;
import com.pioneers.transit.repository.DestinationRepository;
import com.pioneers.transit.repository.LogRepository;
import com.pioneers.transit.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final DestinationRepository destinationRepository;
    private final BusRepository busRepository;

    @Override
    @Transactional
    public LogResponse saveLog(LogRequest request) {
        Destination destination = destinationRepository.findById(request.getDestination().getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ID Destination Not Found"));
        Bus bus = busRepository.findById(request.getBus().getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ID Bus Not Found"));
        Log log = Log.builder()
                .ticketQuantity(request.getTicketQuantity())
                .price(destination.getPrice()+bus.getPrice())
                .hotelKey(request.getHotelKey())
                .hotelUrl(request.getHotelUrl())
                .purchase(request.getPurchase())
                .destination(destination)
                .bus(bus)
                .build();
        logRepository.save(log);
        return toResponse(log);
    }

    @Override
    public PageResponseWrapper<LogResponse> getLog(Pageable pageable) {
        PageResponseWrapper<Log> logPage = new PageResponseWrapper<>(logRepository.findAll(pageable));
        List<LogResponse> responseList = logPage.getData().stream()
                .map(LogServiceImpl::toResponse).toList();
        PageImpl<LogResponse> responses = new PageImpl<>(responseList,pageable, logPage.getTotalElements());
        return new PageResponseWrapper<>(responses);
    }

    private static LogResponse toResponse(Log log){
        return LogResponse.builder()
                .id(log.getId())
                .ticketQuantity(log.getTicketQuantity())
                .price(log.getPrice())
                .purchase(log.getPurchase())
                .destination(log.getDestination())
                .bus(log.getBus())
                .hotelKey(log.getHotelKey())
                .hotelUrl(log.getHotelUrl())
                .build();
    }
}
