package com.hospitalSystem.hospital_system.api.controller;

import com.hospitalSystem.hospital_system.api.model.PatientDetails;
import com.hospitalSystem.hospital_system.api.model.PatientTreatment;
import com.hospitalSystem.hospital_system.repository.PatientRepository;
import com.hospitalSystem.hospital_system.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TreatmentController {
    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    PatientRepository PatientRepository;

    @RequestMapping("/addTreatment/{patientId}")
    @PostMapping()
    public ResponseEntity<PatientTreatment> addTreatment(@RequestBody PatientTreatment patientTreatment, @PathVariable Long patientId){
        Optional<PatientDetails> patientDetails = PatientRepository.findById(patientId);

        patientTreatment.setPatient(patientDetails.get());

        PatientTreatment treatmentResponse = treatmentRepository.save(patientTreatment);
        return ResponseEntity.ok(treatmentResponse);
    }

    @RequestMapping("/viewTreatments")
    @GetMapping()
    public List<PatientTreatment> viewPatientTreatment(){
       List<PatientTreatment> patientTreats = treatmentRepository.findAll();
        return patientTreats;
    }
}
