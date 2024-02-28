package com.pioneers.transit.repository;

import com.pioneers.transit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
