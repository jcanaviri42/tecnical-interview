package com.hospitals.hospital;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    public final HospitalRepository hospitalRepository;
    public final HospitalMapper hospitalMapper;

    public HospitalService(HospitalRepository hospitalRepository, HospitalMapper hospitalMapper) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
    }

    public HospitalResponseDTO saveHospital(HospitalDTO dto) {

        if (this.hospitalRepository.existsByName(dto.name()))
            return null;

        try {
            Hospital hospital = Hospital.builder()
                    .name(dto.name())
                    .phone(dto.phone())
                    .email(dto.email())
                    .createdAt(LocalDate.now())
                    .createdBy("user")
                    .build();

            this.hospitalRepository.save(hospital);
            return this.hospitalMapper.toHospitalResponseDTO(hospital);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving hospital", e);
        }
    }

    public List<HospitalResponseDTO> findAllHospitals() {
        return this.hospitalRepository.findAll()
                .stream()
                .map(this.hospitalMapper::toHospitalResponseDTO)
                .collect(Collectors.toList());
    }

    public HospitalResponseDTO findHospitalById(Long id) {
        try {
            Optional<Hospital> optionalHospital = this.hospitalRepository.findById(id);
            return optionalHospital.map(this.hospitalMapper::toHospitalResponseDTO).orElse(null);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving hospital", e);
        }
    }

    public HospitalResponseDTO updateHospital(Long id, HospitalDTO dto) {
        try {
            Optional<Hospital> optionalHospital = this.hospitalRepository.findById(id);
            if (optionalHospital.isEmpty()) return null;

            Hospital hospital = optionalHospital.get();

            if (dto.name() != null) hospital.setName(dto.name());
            if (dto.phone() != null) hospital.setPhone(dto.phone());
            if (dto.email() != null) hospital.setEmail(dto.email());

            hospital.setUpdatedAt(LocalDate.now());
            // TODO: Get the current user
            hospital.setUpdatedBy("user");

            this.hospitalRepository.save(hospital);

            return this.hospitalMapper.toHospitalResponseDTO(hospital);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving hospital", e);
        }
    }

    public Boolean deleteHospital(Long id) {
        try {
            Optional<Hospital> optionalHospital = this.hospitalRepository.findById(id);
            if (optionalHospital.isEmpty()) return null;
            this.hospitalRepository.delete(optionalHospital.get());
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

}
