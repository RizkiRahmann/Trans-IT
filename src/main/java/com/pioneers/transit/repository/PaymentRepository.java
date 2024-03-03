package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Payment;
import com.pioneers.transit.entity.Role;
import com.pioneers.transit.utils.constant.ERole;
import com.pioneers.transit.utils.constant.EnumPayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,String> {
    Optional<Payment> findByPayment(EnumPayment payment);
}
