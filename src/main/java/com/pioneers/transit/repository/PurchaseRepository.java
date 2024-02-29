package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase,String> {
}
