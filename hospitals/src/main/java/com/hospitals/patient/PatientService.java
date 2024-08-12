package com.hospitals.patient;

import com.hospitals.hospital.Hospital;
import com.hospitals.hospital.HospitalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final PatientMapper patientMapper;

    public PatientService(
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository,
            PatientMapper patientMapper) {

        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientMapper = patientMapper;
    }

    public PatientResponseDTO savePatient(PatientDTO dto) {

        if (this.patientRepository.existsByName(dto.name()))
            return null;

        Optional<Hospital> hospital = this.hospitalRepository.findById(dto.hospitalId());
        if (hospital.isEmpty())
            return null;

        try {
            Patient patient = Patient.builder()
                    .name(dto.name())
                    .lastName(dto.lastName())
                    .birthDate(dto.birthDate())
                    .address(dto.address())
                    .createdAt(LocalDate.now())
                    .createdBy("user")
                    .hospital(hospital.get())
                    .build();

            this.patientRepository.save(patient);
            return this.patientMapper.toPatientResponseDTO(patient);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving Patient", e);
        }
    }

    public List<PatientResponseDTO> findAllPatients() {
        return this.patientRepository.findAll()
                .stream()
                .map(this.patientMapper::toPatientResponseDTO)
                .collect(Collectors.toList());
    }

    public PatientResponseDTO findPatientById(Long id) {
        try {
            Optional<Patient> optionalPatient = this.patientRepository.findById(id);
            return optionalPatient.map(this.patientMapper::toPatientResponseDTO).orElse(null);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving Patient", e);
        }
    }

    public PatientResponseDTO updatePatient(Long id, PatientDTO dto) {
        try {
            Optional<Patient> optionalPatient = this.patientRepository.findById(id);
            if (optionalPatient.isEmpty()) return null;

            Patient Patient = optionalPatient.get();

            if (dto.name() != null) Patient.setName(dto.name());
            if (dto.lastName() != null) Patient.setLastName(dto.lastName());

            Patient.setUpdatedAt(LocalDate.now());
            // TODO: Get the current user
            Patient.setUpdatedBy("user");

            this.patientRepository.save(Patient);

            return this.patientMapper.toPatientResponseDTO(Patient);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving Patient", e);
        }
    }

    public Boolean deletePatient(Long id) {
        try {
            Optional<Patient> optionalPatient = this.patientRepository.findById(id);
            if (optionalPatient.isEmpty()) return null;
            this.patientRepository.delete(optionalPatient.get());
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting Patient", e);
        }
    }

}
