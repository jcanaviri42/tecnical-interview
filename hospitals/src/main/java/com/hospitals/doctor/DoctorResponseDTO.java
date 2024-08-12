package com.hospitals.doctor;

import java.time.LocalDate;

public record DoctorResponseDTO(
        Long id,
        String name,
        String lastName,
        String gravatarUrl,
        LocalDate createdAt,
        LocalDate updatedAt,
        String createdBy,
        String updatedBy
) {
}
