package com.pioneers.transit.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "t_purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @CreatedDate
    private Timestamp purchaseDate;

    @ManyToOne
    private User user;

    //log_id

}
