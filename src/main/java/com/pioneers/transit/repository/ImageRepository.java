package com.pioneers.transit.repository;

import com.pioneers.transit.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,String> {
}
