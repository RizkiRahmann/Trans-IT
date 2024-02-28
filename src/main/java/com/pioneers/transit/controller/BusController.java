package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.BusRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.BusResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.service.BusService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.BUS)
public class BusController {
    private final BusService busService;
    private final BuildResponse buildResponse;

    @PostMapping
    public ResponseEntity<?> createBus(@RequestBody BusRequest busRequest){
        BusResponse busResponse = busService.create(busRequest);
        ControllerResponse<BusResponse> response = buildResponse.response(busResponse, ConstStatus.STATUS_CREATE,"Bus", ConstMessage.M_CREATE);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        BusResponse busResponse = busService.getById(id);
        ControllerResponse<BusResponse> response = buildResponse.response(busResponse, ConstStatus.STATUS_OK, "Bus",ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateBus(@RequestBody BusRequest busRequest){
        BusResponse busResponse = busService.update(busRequest);
        ControllerResponse<BusResponse> response = buildResponse.response(busResponse, ConstStatus.STATUS_CREATE,"Bus", ConstMessage.M_UPDATE);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable String id){
        busService.delete(id);
        ControllerResponse<String> response = buildResponse.response("Data berhasil dihapus",ConstStatus.STATUS_OK,"Bus",ConstMessage.M_DELETE);
        return ResponseEntity.ok(response);
    }
}
