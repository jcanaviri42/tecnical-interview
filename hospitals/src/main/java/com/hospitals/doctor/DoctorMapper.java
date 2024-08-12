package com.hospitals.doctor;

import org.springframework.stereotype.Service;

@Service
public class DoctorMapper {

    public DoctorResponseDTO toDoctorResponseDTO(Doctor doctor) {
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getLastName(),
                doctor.getGravatarUrl(),
                doctor.getCreatedAt(),
                doctor.getUpdatedAt(),
                doctor.getCreatedBy(),
                doctor.getUpdatedBy(),
                doctor.getHospital().getId()
        );
    }

}
