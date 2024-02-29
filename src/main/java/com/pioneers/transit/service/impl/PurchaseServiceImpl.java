package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.PurchaseRequest;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.PurchaseResponse;
import com.pioneers.transit.entity.Purchase;
import com.pioneers.transit.repository.PurchaseRepository;
import com.pioneers.transit.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

    @Override
    public PurchaseResponse create(PurchaseRequest request) {
        Purchase purchase = build(request);
        purchaseRepository.save(purchase);
        return toResponse(purchase);
    }

    @Override
    public PageResponseWrapper<PurchaseResponse> getAll(Pageable pageable) {
        PageResponseWrapper<Purchase> purchasePage = new PageResponseWrapper<>(purchaseRepository.findAll(pageable));
        List<PurchaseResponse> responseList = purchasePage.getData().stream()
                .map(PurchaseServiceImpl::toResponse).toList();
        PageImpl<PurchaseResponse> responses = new PageImpl<>(responseList,pageable,purchasePage.getTotalElements());
        return null;
    }

    @Override
    public PurchaseResponse getById(String id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(null);
        return toResponse(purchase);
    }

    @Override
    public PurchaseResponse update(PurchaseRequest request) {return create(request);}

    @Override
    public void delete(String id) {purchaseRepository.deleteById(id);}
    private static Purchase build(PurchaseRequest request){
        return Purchase.builder()
                .user(request.getUser())
                .build();
    }
    private static PurchaseResponse toResponse(Purchase purchase){
        return PurchaseResponse.builder()
                .id(purchase.getId())
                .user(purchase.getUser())
                .build();
    }
}
