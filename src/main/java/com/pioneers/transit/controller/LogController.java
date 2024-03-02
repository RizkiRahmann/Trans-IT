package com.pioneers.transit.controller;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.response.BuildResponse;
import com.pioneers.transit.dto.response.ControllerResponse;
import com.pioneers.transit.dto.response.LogResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import com.pioneers.transit.entity.Log;
import com.pioneers.transit.service.LogService;
import com.pioneers.transit.utils.constant.ApiUrlConstant;
import com.pioneers.transit.utils.constant.ConstMessage;
import com.pioneers.transit.utils.constant.ConstStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrlConstant.LOG)
public class LogController {
    private final LogService logService;
    private final BuildResponse buildResponse;
    private final String entity="log";

    @PostMapping
    public ResponseEntity<?> saveLog(@RequestBody LogRequest logRequest){
        LogResponse logResponse = logService.saveLog(logRequest);
        ControllerResponse<LogResponse> response = buildResponse.response(logResponse, ConstStatus.STATUS_CREATE,entity, ConstMessage.M_CREATE);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLog(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        PageResponseWrapper<LogResponse> responseWrapper = logService.getLog(pageable);
        ControllerResponse<PageResponseWrapper<LogResponse>> response =buildResponse
                .response(responseWrapper,ConstStatus.STATUS_OK,entity,ConstMessage.M_GET);
        return ResponseEntity.ok(response);
    }
}
