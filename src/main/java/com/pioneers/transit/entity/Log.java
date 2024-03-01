package com.pioneers.transit.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "log_detail")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer ticket_quantity;
    private Integer price;
    private String purchase_id;
    private String destination_id;
    //kurang attribute "hotel_id"
    private String bus_id;

    /*@OneToMany(mappedBy = "log_detail")
    private List<Destination> destinationId = new ArrayList<>();
    @OneToMany(mappedBy = "log_detail")
    private List<Bus> busId = new ArrayList<>();
*/
}
