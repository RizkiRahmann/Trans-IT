package com.pioneers.transit.service;

import com.pioneers.transit.entity.Payment;
import com.pioneers.transit.entity.Role;
import com.pioneers.transit.utils.constant.ERole;
import com.pioneers.transit.utils.constant.EnumPayment;

public interface PaymentService {
    Payment getOrSave(EnumPayment payment);
}
