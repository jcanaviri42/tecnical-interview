package com.hospitals.doctor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Doctor_already_exists");
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

}
