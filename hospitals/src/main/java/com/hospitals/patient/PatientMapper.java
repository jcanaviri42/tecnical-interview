package com.hospitals.patient;

import org.springframework.stereotype.Service;

@Service
public class PatientMapper {

    public PatientResponseDTO toPatientResponseDTO(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getLastName(),
                patient.getBirthDate(),
                patient.getGravatarUrl(),
                patient.getCreatedAt(),
                patient.getUpdatedAt(),
                patient.getCreatedBy(),
                patient.getUpdatedBy(),
                patient.getHospital().getId()
        );
    }

}
