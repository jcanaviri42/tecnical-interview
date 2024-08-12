package com.hospitals.doctor;

public record DoctorDTO(
        String name,
        String lastName,
        Long hospitalId
) {
}
