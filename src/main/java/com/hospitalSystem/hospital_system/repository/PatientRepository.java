package com.hospitalSystem.hospital_system.repository;


import com.hospitalSystem.hospital_system.api.model.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<PatientDetails,Long> {
}
