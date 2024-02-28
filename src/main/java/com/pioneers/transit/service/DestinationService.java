package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.DestinationRequest;
import com.pioneers.transit.dto.response.DestinationResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DestinationService {
    DestinationResponse create(DestinationRequest request);
    PageResponseWrapper<DestinationResponse> getAll(Pageable pageable);
    DestinationResponse getById(String id);
    DestinationResponse update(DestinationRequest request);
    void delete(String id);
}
