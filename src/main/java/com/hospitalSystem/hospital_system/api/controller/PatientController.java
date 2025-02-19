package com.hospitalSystem.hospital_system.api.controller;

import com.hospitalSystem.hospital_system.api.model.PatientDetails;
import com.hospitalSystem.hospital_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @RequestMapping("/addPatient")
    @PostMapping()
    public ResponseEntity<PatientDetails> addPatient(@RequestBody PatientDetails patientDetails) {
        PatientDetails patientResponse = patientRepository.save(patientDetails);
        return ResponseEntity.ok(patientResponse);
    }

    @RequestMapping("/viewPatients")
    @GetMapping()
    public List<PatientDetails> viewPatients() {
        List<PatientDetails> patientList = patientRepository.findAll();
        return patientList;
    }

    @RequestMapping("/viewPatient/{id}")
    @GetMapping()
    public PatientDetails getPatientDetailsById(@PathVariable Long id) {
        PatientDetails patientDetsById = patientRepository.findById(id).orElse(null);
        return patientDetsById;
    }


}
