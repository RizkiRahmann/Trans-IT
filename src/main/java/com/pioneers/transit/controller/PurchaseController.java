package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.PurchaseRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.dto.response.PurchaseResponse;
import com.pioneers.transit.service.PurchaseService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.PURCHASE)
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final BuildResponse buildResponse;
    private final String entity="Purchase";

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @PostMapping
    public ResponseEntity<?> createPurchase(@RequestBody PurchaseRequest request){
        PurchaseResponse purchaseResponse = purchaseService.create(request);
        ControllerResponse<PurchaseResponse> response = buildResponse.response(purchaseResponse, ConstStatus.STATUS_CREATE,entity, ConstMessage.M_CREATE);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        PageResponseWrapper<PurchaseResponse> responseWrapper = purchaseService.getAll(pageable);
        ControllerResponse<PageResponseWrapper<PurchaseResponse>> response =buildResponse
                .response(responseWrapper,ConstStatus.STATUS_OK,entity,ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchaseById(@PathVariable String id){
        PurchaseResponse purchaseResponse = purchaseService.getById(id);
        ControllerResponse<PurchaseResponse> response = buildResponse.response(purchaseResponse, ConstStatus.STATUS_OK,entity, ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public ResponseEntity<?> updatePurchase(@RequestBody PurchaseRequest request){
        PurchaseResponse purchaseResponse = purchaseService.update(request);
        ControllerResponse<PurchaseResponse> response = buildResponse.response(purchaseResponse, ConstStatus.STATUS_CREATE,entity, ConstMessage.M_UPDATE);
        return ResponseEntity.ok(response);
    }
//    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePurchase(@PathVariable String id){
//        purchaseService.delete(id);
//        ControllerResponse<String> response = buildResponse.response("Data berhasil dihapus", ConstStatus.STATUS_OK,entity, ConstMessage.M_DELETE);
//        return ResponseEntity.ok(response);
//    }
}
