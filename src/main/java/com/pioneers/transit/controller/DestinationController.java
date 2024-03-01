package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.DestinationRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.DestinationResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.service.DestinationService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.DESTINATION)
public class DestinationController {
    private final DestinationService destinationService;
    private final BuildResponse buildResponse;
    private final String entity="Destination";

    @PostMapping
    public ResponseEntity<?> createDestination(@RequestBody DestinationRequest destinationRequest){
        DestinationResponse destinationResponse = destinationService.create(destinationRequest);
        ControllerResponse<DestinationResponse> response = buildResponse.response(destinationResponse, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_CREATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                                    @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        PageResponseWrapper<DestinationResponse> destinationResponses = destinationService.getAll(pageable);
        ControllerResponse<PageResponseWrapper<DestinationResponse>> response = buildResponse
                .response(destinationResponses, ConstStatus.STATUS_OK, entity, ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        DestinationResponse destinationResponse = destinationService.getById(id);
        ControllerResponse<DestinationResponse> response = buildResponse.response(destinationResponse, ConstStatus.STATUS_OK, entity, ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateDestination(@RequestBody DestinationRequest destinationRequest){
        DestinationResponse destinationResponse = destinationService.update(destinationRequest);
        ControllerResponse<DestinationResponse> response = buildResponse.response(destinationResponse, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_UPDATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        destinationService.delete(id);
        ControllerResponse<String> response = buildResponse.response("Data berhasil dihapus", ConstStatus.STATUS_OK, entity, ConstMessage.M_DELETE);
        return ResponseEntity.ok(response);
    }

}
