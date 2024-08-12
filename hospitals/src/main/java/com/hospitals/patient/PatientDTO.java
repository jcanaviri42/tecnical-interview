package com.hospitals.patient;

import java.time.LocalDate;

public record PatientDTO(
        String name,
        String lastName,
        String address,
        LocalDate birthDate,
        Long hospitalId
) {
}
