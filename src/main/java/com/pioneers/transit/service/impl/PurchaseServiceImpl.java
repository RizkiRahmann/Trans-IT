package com.pioneers.transit.service.impl;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.request.PurchaseRequest;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.HotelResponseClient;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.PurchaseResponse;
import com.pioneers.transit.entity.*;
import com.pioneers.transit.repository.BusRepository;
import com.pioneers.transit.repository.PurchaseRepository;
import com.pioneers.transit.repository.UserRepository;
import com.pioneers.transit.service.*;
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
    private final ValidationService validationService;
    private final PaymentService paymentService;
    private final HotelServiceClient hotelServiceClient;

    @Override
    @Transactional
    public PurchaseResponse create(PurchaseRequest request) {
        validationService.validate(request);
        Payment payment = paymentService.getOrSave(request.getPayment());
        User user = userRepository.findById(request.getUser().getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ID User Not Found"));
        Purchase purchase = Purchase.builder()
                .purchaseDate(request.getPurchaseDate())
                .checkIn(request.getChkIn())
                .checkOut(request.getChkOut())
                .payment(payment)
                .user(user)
                .logs(request.getLogs())
                .build();
        Purchase purchaseSave = purchaseRepository.save(purchase);
        for (Log log : purchase.getLogs()){
            log.setPurchase(purchaseSave);
            Bus bus = busRepository.findById(log.getBus().getId())
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ID Bus Not Found"));
            if (bus.getChair() < 1 || bus.getChair()-log.getTicketQuantity()<0) throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Full...");
            bus.setChair(bus.getChair()-log.getTicketQuantity());
            LogRequest logRequest = LogRequest.builder()
                    .ticketQuantity(log.getTicketQuantity())
                    .price(log.getPrice())
                    .hotelKey(log.getHotelKey())
                    .hotelUrl(log.getHotelUrl())
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
    public PurchaseResponse update(PurchaseRequest request) {
        validationService.validate(request);
        Purchase purchase = purchaseRepository.findById(request.getId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Id purchase NOT FOUND"));
        purchase.setPurchaseDate(request.getPurchaseDate());
        purchase.setPayment(paymentService.getOrSave(request.getPayment()));
        purchaseRepository.save(purchase);
        return toResponse(purchase);
    }

    @Override
    public void delete(String id) {
        purchaseRepository.deleteById(id);
    }


    private static PurchaseResponse toResponse(Purchase purchase){
        return PurchaseResponse.builder()
                .id(purchase.getId())
                .purchaseDate(purchase.getPurchaseDate())
                .checkIn(purchase.getCheckIn())
                .checkOut(purchase.getCheckOut())
                .payment(purchase.getPayment())
                .user(purchase.getUser())
                .logs(purchase.getLogs())
                .build();
    }
}
