package com.hospitalSystem.hospital_system.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "patients")
public class PatientDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String dob;
    @Column(nullable = false)
    private String gender;
    @Column
    private String phone;
    @Column
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String nextOfKin;
    @Column(nullable = false)
    private String medicalaid;
}
