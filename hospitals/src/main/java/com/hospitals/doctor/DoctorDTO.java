package com.hospitals.doctor;

import java.time.LocalDate;
import java.util.List;

public record DoctorDTO(
        String name,
        String lastName,
        LocalDate birthDate,
        Long hospitalId,
        List<Long> specialitiesIds
) {
}
