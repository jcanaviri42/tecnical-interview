package com.hospitals.hospital;

import org.springframework.stereotype.Service;

@Service
public class HospitalMapper {

    public HospitalResponseDTO toHospitalResponseDTO(Hospital hospital) {
        return new HospitalResponseDTO(
                hospital.getId(),
                hospital.getName(),
                hospital.getPhone(),
                hospital.getEmail(),
                hospital.getGravatarUrl(),
                hospital.getCreatedAt(),
                hospital.getUpdatedAt(),
                hospital.getCreatedBy(),
                hospital.getUpdatedBy()
        );
    }

}
