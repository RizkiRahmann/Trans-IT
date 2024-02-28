package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.BusRequest;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BusService {
    BusResponse create(BusRequest request);
    PageResponseWrapper<BusResponse> getAll(Pageable pageable);
    BusResponse getById(String id);
    BusResponse update(BusRequest request);
    void delete(String id);
}
