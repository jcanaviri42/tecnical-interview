package com.hospitals.speciality;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialities")
@SuppressWarnings("unused")
public class SpecialityController {

    private final SpecialityService specialityService;

    public SpecialityController(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @PostMapping
    public ResponseEntity<?> saveSpeciality(@RequestBody SpecialityDTO dto) {
        try {
            SpecialityResponseDTO savedSpecialityDTO = this.specialityService.saveSpeciality(dto);
            if (savedSpecialityDTO == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("The speciality already exists.");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSpecialityDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<SpecialityResponseDTO> findAllSpecialities() {
        return this.specialityService.findAllSpecialities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSpecialityById(@PathVariable Long id) {
        try {
            SpecialityResponseDTO specialityDTO = this.specialityService.findSpecialityById(id);
            if (specialityDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Speciality not found");
            return ResponseEntity.status(HttpStatus.OK).body(specialityDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpeciality(@PathVariable Long id, @RequestBody SpecialityDTO dto) {
        try {
            SpecialityResponseDTO specialityDTO = this.specialityService.updateSpeciality(id, dto);
            if (specialityDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Speciality not found");
            return ResponseEntity.status(HttpStatus.OK).body(specialityDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpeciality(@PathVariable Long id) {
        try {
            Boolean hasBeenDeleted = this.specialityService.deleteSpeciality(id);
            if (hasBeenDeleted == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Speciality not found");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't not delete");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Speciality deleted");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
