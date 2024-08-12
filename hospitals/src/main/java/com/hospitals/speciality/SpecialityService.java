package com.hospitals.speciality;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialityService {
    private final SpecialityRepository specialityRepository;
    private final SpecialityMapper specialityMapper;

    public SpecialityService(SpecialityRepository specialityRepository, SpecialityMapper specialityMapper) {
        this.specialityRepository = specialityRepository;
        this.specialityMapper = specialityMapper;
    }

    public SpecialityResponseDTO saveSpecialty(SpecialityDTO dto) {

        if (this.specialityRepository.existsByName(dto.name()))
            return null;

        try {
            Speciality specialty = Speciality.builder()
                    .name(dto.name())
                    .description(dto.description())
                    .createdAt(LocalDate.now())
                    .createdBy("user")
                    .build();

            this.specialityRepository.save(specialty);
            return this.specialityMapper.toSpecialityDTO(specialty);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving specialty", e);
        }
    }

    public List<SpecialityResponseDTO> findAllSpecialties() {
        return this.specialityRepository.findAll()
                .stream()
                .map(this.specialityMapper::toSpecialityDTO)
                .collect(Collectors.toList());
    }

    public SpecialityResponseDTO findSpecialityById(Long id) {
        try {
            Optional<Speciality> optionalSpeciality = this.specialityRepository.findById(id);
            return optionalSpeciality.map(this.specialityMapper::toSpecialityDTO).orElse(null);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving specialty", e);
        }
    }

    public SpecialityResponseDTO updateSpecialty(Long id, SpecialityDTO dto) {
        try {
            Optional<Speciality> optionalSpeciality = this.specialityRepository.findById(id);
            if (optionalSpeciality.isEmpty()) return null;

            Speciality speciality = optionalSpeciality.get();

            if (dto.name() != null) speciality.setName(dto.name());
            if (dto.description() != null) speciality.setDescription(dto.description());

            speciality.setUpdatedAt(LocalDate.now());
            // TODO: Get the current user
            speciality.setUpdatedBy("user");

            this.specialityRepository.save(speciality);

            return this.specialityMapper.toSpecialityDTO(speciality);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving specialty", e);
        }
    }

    public Boolean deleteSpeciality(Long id) {
        try {
            Optional<Speciality> optionalSpecialty = this.specialityRepository.findById(id);
            if (optionalSpecialty.isEmpty()) return null;
            this.specialityRepository.delete(optionalSpecialty.get());
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting specialty", e);
        }
    }
}
