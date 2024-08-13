package com.hospitals.doctor;

import com.hospitals.patient.PatientResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@SuppressWarnings("unused")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<?> saveDoctor(@RequestBody DoctorDTO dto) {
        try {
            DoctorResponseDTO savedDoctorDTO = this.doctorService.saveDoctor(dto);
            if (savedDoctorDTO == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("could_not_create_doctor");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctorDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<DoctorResponseDTO> findAllDoctors() {
        return this.doctorService.findAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDoctorById(@PathVariable Long id) {
        try {
            DoctorResponseDTO DoctorDTO = this.doctorService.findDoctorById(id);
            if (DoctorDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor_not_found");
            return ResponseEntity.status(HttpStatus.OK).body(DoctorDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO dto) {
        try {
            DoctorResponseDTO DoctorDTO = this.doctorService.updateDoctor(id, dto);
            if (DoctorDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor_not_found");
            return ResponseEntity.status(HttpStatus.OK).body(DoctorDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        try {
            Boolean hasBeenDeleted = this.doctorService.deleteDoctor(id);
            if (hasBeenDeleted == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor_not_found");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("could_not_delete");

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
                List<DoctorResponseDTO> doctors = this.doctorService.findAllDoctorsByNameAndLastName(name, lastName);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }
            if (name != null) {
                List<DoctorResponseDTO> doctors = this.doctorService.findAllDoctorsByName(name);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }
            if (lastName != null) {
                List<DoctorResponseDTO> doctors = this.doctorService.findAllDoctorsByLastName(lastName);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }

            if (startDate != null && endDate != null) {
                List<DoctorResponseDTO> doctors = this.doctorService.findAllByCreatedAtBetween(startDate, endDate);
                return ResponseEntity.status(HttpStatus.OK).body(doctors);
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("any_doctor_found");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<?> getAllPatientsByDoctor(@PathVariable Long id) {
        try {
            List<PatientResponseDTO> allPatients = this.doctorService.findAllPatients(id);
            return ResponseEntity.status(HttpStatus.OK).body(allPatients);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
