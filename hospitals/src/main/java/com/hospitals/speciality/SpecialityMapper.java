package com.hospitals.speciality;

import org.springframework.stereotype.Service;

@Service
public class SpecialityMapper {

    public SpecialityResponseDTO toSpecialityDTO(Speciality speciality) {
        return new SpecialityResponseDTO(
                speciality.getId(),
                speciality.getName(),
                speciality.getDescription(),
                speciality.getGravatarUrl(),
                speciality.getCreatedAt(),
                speciality.getUpdatedAt(),
                speciality.getCreatedBy(),
                speciality.getUpdatedBy()
        );
    }

}
