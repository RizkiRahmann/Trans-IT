package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.PurchaseRequest;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.PurchaseResponse;
import org.springframework.data.domain.Pageable;

public interface PurchaseService {
    PurchaseResponse create(PurchaseRequest request);
    PageResponseWrapper<PurchaseResponse> getAll(Pageable pageable);
    PurchaseResponse getById(String id);
    PurchaseResponse update(PurchaseRequest request);
    void delete(String id);
}
