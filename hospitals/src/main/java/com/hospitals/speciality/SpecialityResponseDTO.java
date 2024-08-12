package com.hospitals.speciality;

import java.time.LocalDate;

public record SpecialityResponseDTO(
        Long id,
        String name,
        String description,
        String gravatarUrl,
        LocalDate createdAt,
        LocalDate updatedAt,
        String createdBy,
        String updatedBy
) {
}
