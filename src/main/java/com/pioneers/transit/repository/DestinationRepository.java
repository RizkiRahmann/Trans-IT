package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, String> {

}
