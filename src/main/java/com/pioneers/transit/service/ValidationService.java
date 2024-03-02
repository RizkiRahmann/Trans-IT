package com.pioneers.transit.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ValidationService {
    private final Validator validator;

    public void validate(Object request){
        Set<ConstraintViolation<Object>> constarintViolations = validator.validate(request);
        if (constarintViolations.size() != 0) {
            // error
            throw new ConstraintViolationException(constarintViolations);
        }
    }

}
