package com.hospitals.hospital;

import java.time.LocalDate;

public record HospitalResponseDTO(
        Long id,
        String name,
        String phone,
        String email,
        String gravatarUrl,
        LocalDate createdAt,
        LocalDate updatedAt,
        String createdBy,
        String updatedBy
) {
}
