package com.pioneers.transit.service;

import com.pioneers.transit.dto.request.LogRequest;
import com.pioneers.transit.dto.response.LogResponse;
import com.pioneers.transit.dto.response.PageResponseWrapper;
import org.springframework.data.domain.Pageable;


public interface LogService {
    LogResponse saveLog(LogRequest request);
    PageResponseWrapper<LogResponse> getLog(Pageable pageable);
}
