package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.request.PurchaseRequest;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.PurchaseResponse;
import com.pioneers.transit.entity.Bus;
import com.pioneers.transit.entity.Log;
import com.pioneers.transit.entity.Purchase;
import com.pioneers.transit.entity.User;
import com.pioneers.transit.repository.BusRepository;
import com.pioneers.transit.repository.PurchaseRepository;
import com.pioneers.transit.repository.UserRepository;
import com.pioneers.transit.service.BusService;
import com.pioneers.transit.service.LogService;
import com.pioneers.transit.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final LogService logService;
    private final BusRepository busRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PurchaseResponse create(PurchaseRequest request) {
        User user = userRepository.findById(request.getUser().getId()).orElseThrow(null);
        Purchase purchase = Purchase.builder()
                .purchaseDate(request.getPurchaseDate())
                .user(user)
                .logs(request.getLogs())
                .build();
        Purchase purchaseSave = purchaseRepository.save(purchase);
        for (Log log : request.getLogs()){
            log.setPurchase(purchaseSave);
            Bus bus = busRepository.findById(log.getBus().getId()).orElseThrow(null);
            if (bus.getChair() < 1 || bus.getChair()-log.getTicketQuantity()<0) throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Kursi penuh");
            bus.setChair(bus.getChair()-log.getTicketQuantity());
            LogRequest logRequest = LogRequest.builder()
                    .id(log.getId())
                    .ticketQuantity(log.getTicketQuantity())
                    .price(log.getPrice())
                    .purchase(log.getPurchase())
                    .destination(log.getDestination())
                    .bus(log.getBus())
                    .build();
            logService.saveLog(logRequest);
        }
        return toResponse(purchaseSave);
    }

    @Override
    public PageResponseWrapper<PurchaseResponse> getAll(Pageable pageable) {
        PageResponseWrapper<Purchase> purchasePage = new PageResponseWrapper<>(purchaseRepository.findAll(pageable));
        List<PurchaseResponse> responseList = purchasePage.getData().stream()
                .map(PurchaseServiceImpl::toResponse).toList();
        PageImpl<PurchaseResponse> responses = new PageImpl<>(responseList,pageable,purchasePage.getTotalElements());
        return new PageResponseWrapper<>(responses);
    }

    @Override
    public PurchaseResponse getById(String id) {
        Purchase purchase = purchaseRepository.findById(id).orElseThrow(null);
        return toResponse(purchase);
    }

    @Override
    @Transactional
    public PurchaseResponse update(PurchaseRequest request) {return create(request);}

    @Override
    public void delete(String id) {
        purchaseRepository.deleteById(id);
    }


    private static PurchaseResponse toResponse(Purchase purchase){
        return PurchaseResponse.builder()
                .id(purchase.getId())
                .purchaseDate(purchase.getPurchaseDate())
                .user(purchase.getUser())
                .logs(purchase.getLogs())
                .build();
    }
}
