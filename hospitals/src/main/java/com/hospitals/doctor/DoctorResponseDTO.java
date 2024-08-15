package com.hospitals.doctor;

import com.hospitals.speciality.Speciality;

import java.time.LocalDate;
import java.util.Set;

public record DoctorResponseDTO(
        Long id,
        String name,
        String lastName,
        LocalDate birthDate,
        String gravatarUrl,
        LocalDate createdAt,
        LocalDate updatedAt,
        String createdBy,
        String updatedBy,
        Long hospitalId,
        Set<Speciality> specialities
) {
}
