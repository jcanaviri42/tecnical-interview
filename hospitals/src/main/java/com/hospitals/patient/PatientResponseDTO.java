package com.hospitals.patient;

import java.time.LocalDate;

public record PatientResponseDTO(
        Long id,
        String name,
        String lastName,
        LocalDate birthDate,
        String gravatarUrl,
        LocalDate createdAt,
        LocalDate updatedAt,
        String createdBy,
        String updatedBy,
        Long hospitalId
) {
}
