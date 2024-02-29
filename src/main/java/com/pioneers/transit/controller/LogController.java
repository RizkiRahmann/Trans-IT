package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.response.*;
import com.pioneers.transit.service.LogService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.LOG)
public class LogController {
    private final LogService logService;
    private final BuildResponse buildResponse;

    private final String entity="Log";

    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody LogRequest logRequest){
        LogResponse logResponse = logService.create(logRequest);
        ControllerResponse<LogResponse> response = buildResponse.response(logResponse, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_CREATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "size", defaultValue = "5") Integer size,
                                    @RequestParam(name = "sort-by", defaultValue = "name") String sortBy,
                                    @RequestParam(name = "direction", defaultValue = "ASC") String direction){
    Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
    Pageable pageable = PageRequest.of(page, size, sort);
    PageResponseWrapper<LogResponse> logResponse = logService.getAll(pageable);
    ControllerResponse<PageResponseWrapper<LogResponse>> response = buildResponse
            .response(logResponse, ConstStatus.STATUS_OK, entity, ConstMessage.M_GET);
    return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateLog(@RequestBody LogRequest logRequest){
        LogResponse logResponse = logService.update(logRequest);
        ControllerResponse<LogResponse> response = buildResponse.response(logResponse, ConstStatus.STATUS_CREATE, entity, ConstMessage.M_UPDATE);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        logService.delete(id);
        ControllerResponse<String> response = buildResponse.response("Data berhasil dihapus", ConstStatus.STATUS_OK, entity, ConstMessage.M_DELETE);
        return ResponseEntity.ok(response);
    }


}

