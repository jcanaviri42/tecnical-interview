package com.hospitals.patient;

import com.hospitals.doctor.DoctorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't create the patient");
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found.");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't delete the patient");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate
    ) {

        try {
            if (name != null && lastName != null) {
                List<PatientResponseDTO> doctors = this.patientService.findAllPatientsByNameAndLastName(name, lastName);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }
            if (name != null) {
                List<PatientResponseDTO> doctors = this.patientService.findAllPatientByName(name);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }
            if (lastName != null) {
                List<PatientResponseDTO> doctors = this.patientService.findAllPatientByLastName(lastName);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }
            if (startDate != null && endDate != null) {

                List<PatientResponseDTO> doctorsByCreation = this.patientService
                        .findAllByCreatedAtBetween(startDate, endDate);
                if (!doctorsByCreation.isEmpty())
                    return ResponseEntity.status(HttpStatus.OK).body(doctorsByCreation);

                List<PatientResponseDTO> doctorsByBirthDate = this.patientService
                        .findAllByBirthDateBetween(startDate, endDate);
                if (!doctorsByBirthDate.isEmpty())
                    return ResponseEntity.status(HttpStatus.OK).body(doctorsByBirthDate);

            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Any patient found.");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/doctors")
    public ResponseEntity<?> getAllDoctorsByPatient(@PathVariable Long id) {
        try {
            List<DoctorResponseDTO> allDoctors = this.patientService.findAllDoctors(id);
            return ResponseEntity.status(HttpStatus.OK).body(allDoctors);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
