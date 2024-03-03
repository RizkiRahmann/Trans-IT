package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.Payment;
import com.pioneers.transit.entity.Role;
import com.pioneers.transit.repository.PaymentRepository;
import com.pioneers.transit.service.PaymentService;
import com.pioneers.transit.utils.constant.ERole;
import com.pioneers.transit.utils.constant.EnumPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    @Override
    public Payment getOrSave(EnumPayment payment) {
        Optional<Payment> byPayment = paymentRepository.findByPayment(payment);
        if (byPayment.isPresent()) return byPayment.get();

        Payment paymentInstance = Payment.builder()
                .payment(payment)
                .build();
        return paymentRepository.save(paymentInstance);
    }
}
