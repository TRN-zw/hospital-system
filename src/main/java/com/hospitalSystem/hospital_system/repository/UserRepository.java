package com.hospitalSystem.hospital_system.repository;

import com.hospitalSystem.hospital_system.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
