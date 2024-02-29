package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.LogResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Log;
import com.pioneers.transit.repository.LogRepository;
import com.pioneers.transit.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService{
    private final LogRepository logRepository;

    @Override
    public LogResponse create(LogRequest request) {
        Log log = Log.builder()
                .ticket_quantity(request.getTicket_quantity())
                .price(request.getPrice())
                .purchase_id(request.getPurchase_id())
                .destination_id(request.getDestination_id())
                .bus_id(request.getBus_id())
                .build();
        logRepository.save(log);
        return toLogResponse(log);
    }

    @Override
    public PageResponseWrapper<LogResponse> getAll(Pageable pageable) {
        Page<Log> logs = logRepository.findAll(pageable);
        List<LogResponse> logResponses = logs.getContent().stream()
                .map(LogServiceImpl::toLogResponse).toList();
        PageImpl<LogResponse> responses = new PageImpl<>(logResponses, pageable, logs.getTotalElements());
        return new PageResponseWrapper<>(responses);
    }

    @Override
    public LogResponse getById(String id) {
        Log log = logRepository.findById(id).orElseThrow(null);
        return toLogResponse(log);
    }

    @Override
    public LogResponse update(LogRequest request) {
        return create(request);
    }

    @Override
    public void delete(String id) {
        logRepository.deleteById(id);
    }

    private static LogResponse toLogResponse(Log log){
        return LogResponse.builder()
                .id(log.getId())
                .ticket_quantity(log.getTicket_quantity())
                .price(log.getPrice())
                .purchase_id(log.getPurchase_id())
                .destination_id(log.getDestination_id())
                .bus_id(log.getBus_id())
                .build();
    }
}
