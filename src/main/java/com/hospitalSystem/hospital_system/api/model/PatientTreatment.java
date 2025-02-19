package com.hospitalSystem.hospital_system.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patient_treatments")
public class PatientTreatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientDetails patient;

    @Column(nullable = false)
    private LocalDateTime treatmentDate;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String medication;

    @Column(nullable = false)
    private String dosage;
}
