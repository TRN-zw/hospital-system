package com.hospitalSystem.hospital_system.repository;

import com.hospitalSystem.hospital_system.api.model.PatientTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<PatientTreatment,Long> {
}
