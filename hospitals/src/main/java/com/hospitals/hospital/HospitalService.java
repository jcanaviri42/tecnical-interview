package com.hospitals.hospital;

import com.hospitals.doctor.DoctorMapper;
import com.hospitals.doctor.DoctorResponseDTO;
import com.hospitals.patient.PatientMapper;
import com.hospitals.patient.PatientResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    public final HospitalRepository hospitalRepository;
    public final HospitalMapper hospitalMapper;
    public final DoctorMapper doctorMapper;
    public final PatientMapper patientMapper;

    public HospitalService(
            HospitalRepository hospitalRepository,
            HospitalMapper hospitalMapper,
            DoctorMapper doctorMapper,
            PatientMapper patientMapper) {

        this.hospitalRepository = hospitalRepository;
        this.hospitalMapper = hospitalMapper;
        this.doctorMapper = doctorMapper;
        this.patientMapper = patientMapper;
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

    public List<HospitalResponseDTO> findByName(String q) {
        try {
            return this.hospitalRepository.findAllByNameContainingIgnoreCase(q)
                    .stream()
                    .map(this.hospitalMapper::toHospitalResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

    public List<HospitalResponseDTO> findByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        try {
            return this.hospitalRepository.findAllByCreatedAtBetween(startDate, endDate)
                    .stream()
                    .map(this.hospitalMapper::toHospitalResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public List<DoctorResponseDTO> findAllDoctors(Long id) {
        try {
            Optional<Hospital> optionalHospital = this.hospitalRepository.findById(id);
            if (optionalHospital.isEmpty()) return null;
            Hospital hospital = optionalHospital.get();
            return hospital.getDoctors()
                    .stream()
                    .map(this.doctorMapper::toDoctorResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

    public List<PatientResponseDTO> findAllPatients(Long id) {
        try {
            Optional<Hospital> optionalHospital = this.hospitalRepository.findById(id);
            if (optionalHospital.isEmpty()) return null;
            Hospital hospital = optionalHospital.get();
            return hospital.getPatients()
                    .stream()
                    .map(this.patientMapper::toPatientResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

}
