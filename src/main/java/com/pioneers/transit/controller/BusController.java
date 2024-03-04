package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.BusRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.service.BusService;
import com.pioneers.transit.specification.bus.BusSearchDTO;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.BUS)
public class BusController {
    private final BusService busService;
    private final BuildResponse buildResponse;
    private final String entity="Bus";

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusRequest busRequest){
        BusResponse busResponse = busService.create(busRequest);
        ControllerResponse<BusResponse> response = buildResponse.response(busResponse, ConstStatus.STATUS_CREATE,entity, ConstMessage.M_CREATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @ModelAttribute(binding = false) BusSearchDTO busSearchDTO){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        PageResponseWrapper<BusResponse> responseWrapper  = busService.getAll(pageRequest,busSearchDTO);
        ControllerResponse<PageResponseWrapper<BusResponse>> response =buildResponse
                .response(responseWrapper,ConstStatus.STATUS_OK,entity,ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        BusResponse busResponse = busService.getById(id);
        ControllerResponse<BusResponse> response = buildResponse.response(busResponse, ConstStatus.STATUS_OK, entity,ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateBus(@RequestBody BusRequest busRequest){
        BusResponse busResponse = busService.update(busRequest);
        ControllerResponse<BusResponse> response = buildResponse.response(busResponse, ConstStatus.STATUS_CREATE,entity, ConstMessage.M_UPDATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable String id){
        busService.delete(id);
        ControllerResponse<String> response = buildResponse.response("Data berhasil dihapus",ConstStatus.STATUS_OK,entity,ConstMessage.M_DELETE);
        return ResponseEntity.ok(response);
    }
}
