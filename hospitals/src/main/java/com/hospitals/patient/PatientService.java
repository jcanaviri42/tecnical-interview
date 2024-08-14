package com.hospitals.patient;

import com.hospitals.doctor.DoctorMapper;
import com.hospitals.doctor.DoctorResponseDTO;
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
    private final DoctorMapper doctorMapper;

    public PatientService(
            PatientRepository patientRepository,
            HospitalRepository hospitalRepository,
            PatientMapper patientMapper,
            DoctorMapper doctorMapper) {

        this.patientRepository = patientRepository;
        this.hospitalRepository = hospitalRepository;
        this.patientMapper = patientMapper;
        this.doctorMapper = doctorMapper;
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

            Patient patient = optionalPatient.get();

            if (dto.name() != null) patient.setName(dto.name());
            if (dto.lastName() != null) patient.setLastName(dto.lastName());
            if (dto.address() != null) patient.setAddress(dto.address());
            if (dto.birthDate() != null) patient.setBirthDate(dto.birthDate());

            patient.setUpdatedAt(LocalDate.now());
            // TODO: Get the current user
            patient.setUpdatedBy("user");

            this.patientRepository.save(patient);

            return this.patientMapper.toPatientResponseDTO(patient);
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

    public List<PatientResponseDTO> findAllPatientsByNameAndLastName(String name, String lastName) {
        try {
            return this.patientRepository.findAllByNameAndLastNameContainingIgnoreCase(name, lastName)
                    .stream()
                    .map(this.patientMapper::toPatientResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public List<PatientResponseDTO> findAllPatientByName(String name) {
        try {
            return this.patientRepository.findAllByNameContainingIgnoreCase(name)
                    .stream()
                    .map(this.patientMapper::toPatientResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public List<PatientResponseDTO> findAllPatientByLastName(String lastName) {
        try {
            return this.patientRepository.findAllByLastNameContainingIgnoreCase(lastName)
                    .stream()
                    .map(this.patientMapper::toPatientResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public List<PatientResponseDTO> findAllByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        try {
            return this.patientRepository.findAllByCreatedAtBetween(startDate, endDate)
                    .stream()
                    .map(this.patientMapper::toPatientResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public List<PatientResponseDTO> findAllByBirthDateBetween(LocalDate startDate, LocalDate endDate) {
        try {
            return this.patientRepository.findAllByBirthDateBetween(startDate, endDate)
                    .stream()
                    .map(this.patientMapper::toPatientResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error", e);
        }
    }

    public List<DoctorResponseDTO> findAllDoctors(Long id) {
        try {
            Optional<Patient> optionalPatient = this.patientRepository.findById(id);
            if (optionalPatient.isEmpty()) return null;

            Patient patient = optionalPatient.get();
            return patient.getNotes()
                    .stream()
                    .map(note -> this.doctorMapper.toDoctorResponseDTO(note.getDoctor()))
                    .distinct()
                    .collect(Collectors.toList());

        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

}
