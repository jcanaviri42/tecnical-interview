package com.hospitals.patient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@SuppressWarnings("unused")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<?> savePatient(@RequestBody PatientDTO dto) {
        try {
            PatientResponseDTO savedPatientDTO = this.patientService.savePatient(dto);
            if (savedPatientDTO == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("could_not_create_Patient");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPatientDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<PatientResponseDTO> findAllPatients() {
        return this.patientService.findAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPatientById(@PathVariable Long id) {
        try {
            PatientResponseDTO patientDTO = this.patientService.findPatientById(id);
            if (patientDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient_not_found");
            return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody PatientDTO dto) {
        try {
            PatientResponseDTO patientDTO = this.patientService.updatePatient(id, dto);
            if (patientDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient_not_found");
            return ResponseEntity.status(HttpStatus.OK).body(patientDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            Boolean hasBeenDeleted = this.patientService.deletePatient(id);
            if (hasBeenDeleted == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient_not_found");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("could_not_delete");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
