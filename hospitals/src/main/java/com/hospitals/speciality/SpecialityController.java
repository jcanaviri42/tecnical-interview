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
    public ResponseEntity<?> saveSpecialty(@RequestBody SpecialityDTO dto) {
        try {
            SpecialityResponseDTO savedSpecialityDTO = this.specialityService.saveSpecialty(dto);
            if (savedSpecialityDTO == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("speciality_already_exists");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedSpecialityDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<SpecialityResponseDTO> findAllSpecialties() {
        return this.specialityService.findAllSpecialties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findSpecialityById(@PathVariable Long id) {
        try {
            SpecialityResponseDTO specialtyDTO = this.specialityService.findSpecialityById(id);
            if (specialtyDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("specialty_not_found");
            return ResponseEntity.status(HttpStatus.OK).body(specialtyDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSpeciality(@PathVariable Long id, @RequestBody SpecialityDTO dto) {
        try {
            SpecialityResponseDTO specialtyDTO = this.specialityService.updateSpecialty(id, dto);
            if (specialtyDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("specialty_not_found");
            return ResponseEntity.status(HttpStatus.OK).body(specialtyDTO);
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("specialty_not_found");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("could_not_delete");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("deleted");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
