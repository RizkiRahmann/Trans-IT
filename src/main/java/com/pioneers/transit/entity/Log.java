package com.pioneers.transit.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import javax.print.attribute.standard.Destination;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer ticketQuantity;
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    @JsonIgnoreProperties("logs")
    private Purchase purchase;
    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;
//    @ManyToOne
//    @JoinColumn(name = "destination_id")
//    private Destination destination;
}
