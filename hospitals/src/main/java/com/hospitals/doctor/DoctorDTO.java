package com.hospitals.doctor;

import java.time.LocalDate;

public record DoctorDTO(
        String name,
        String lastName,
        LocalDate birthDate,
        Long hospitalId
) {
}
