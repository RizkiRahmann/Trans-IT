package com.pioneers.transit.dto.response;

import org.springframework.stereotype.Component;

@Component
public class BuildResponse {

    public <T> ControllerResponse<T> response(T data, String status, String entity, String message) {
        return ControllerResponse.<T>builder()
                .status(status)
                .message(String.format(message, entity))
                .data(data)
                .build();
    }
}
