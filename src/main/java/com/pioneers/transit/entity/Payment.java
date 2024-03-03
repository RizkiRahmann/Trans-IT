package com.pioneers.transit.entity;

import com.pioneers.transit.utils.constant.ERole;
import com.pioneers.transit.utils.constant.EnumPayment;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "m_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private EnumPayment payment;
}
