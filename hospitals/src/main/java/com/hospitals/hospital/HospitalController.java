package com.hospitals.hospital;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
@SuppressWarnings("unused")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<?> saveHospital(@RequestBody HospitalDTO dto) {
        try {
            HospitalResponseDTO savedHospitalDTO = this.hospitalService.saveHospital(dto);
            if (savedHospitalDTO == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Hospital already exists.");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHospitalDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<HospitalResponseDTO> findAllHospitals() {
        return this.hospitalService.findAllHospitals();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findHospitalById(@PathVariable Long id) {
        try {
            HospitalResponseDTO hospitalDTO = this.hospitalService.findHospitalById(id);
            if (hospitalDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found.");
            return ResponseEntity.status(HttpStatus.OK).body(hospitalDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHospital(@PathVariable Long id, @RequestBody HospitalDTO dto) {
        try {
            HospitalResponseDTO hospitalDTO = this.hospitalService.updateHospital(id, dto);
            if (hospitalDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found.");
            return ResponseEntity.status(HttpStatus.OK).body(hospitalDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHospital(@PathVariable Long id) {
        try {
            Boolean hasBeenDeleted = this.hospitalService.deleteHospital(id);
            if (hasBeenDeleted == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found.");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't delete.");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Hospital deleted.");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByName(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "startDate", required = false) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate
    ) {
        try {
            if (q != null) {
                List<HospitalResponseDTO> hospitals = this.hospitalService.findByName(q);
                return ResponseEntity.status(HttpStatus.OK).body(hospitals);
            }

            if (startDate != null && endDate != null) {
                List<HospitalResponseDTO> hospitals = this.hospitalService
                        .findByCreatedAtBetween(startDate, endDate);
                return ResponseEntity.status(HttpStatus.OK).body(hospitals);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Any hospital found.");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
