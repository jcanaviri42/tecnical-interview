package com.hospitals.doctor;

import com.hospitals.hospital.Hospital;
import com.hospitals.hospital.HospitalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    private final DoctorMapper doctorMapper;

    public DoctorService(
            DoctorRepository doctorRepository,
            HospitalRepository hospitalRepository,
            DoctorMapper doctorMapper) {

        this.doctorRepository = doctorRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorMapper = doctorMapper;
    }

    public DoctorResponseDTO saveDoctor(DoctorDTO dto) {

        if (this.doctorRepository.existsByName(dto.name()))
            return null;

        Optional<Hospital> hospital = this.hospitalRepository.findById(dto.hospitalId());
        if (hospital.isEmpty())
            return null;

        try {
            Doctor doctor = Doctor.builder()
                    .name(dto.name())
                    .lastName(dto.lastName())
                    .createdAt(LocalDate.now())
                    .createdBy("user")
                    .hospital(hospital.get())
                    .build();

            this.doctorRepository.save(doctor);
            return this.doctorMapper.toDoctorResponseDTO(doctor);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving doctor", e);
        }
    }

    public List<DoctorResponseDTO> findAllDoctors() {
        return this.doctorRepository.findAll()
                .stream()
                .map(this.doctorMapper::toDoctorResponseDTO)
                .collect(Collectors.toList());
    }

    public DoctorResponseDTO findDoctorById(Long id) {
        try {
            Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
            return optionalDoctor.map(this.doctorMapper::toDoctorResponseDTO).orElse(null);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving doctor", e);
        }
    }

    public DoctorResponseDTO updateDoctor(Long id, DoctorDTO dto) {
        try {
            Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
            if (optionalDoctor.isEmpty()) return null;

            Doctor Doctor = optionalDoctor.get();

            if (dto.name() != null) Doctor.setName(dto.name());
            if (dto.lastName() != null) Doctor.setLastName(dto.lastName());

            Doctor.setUpdatedAt(LocalDate.now());
            // TODO: Get the current user
            Doctor.setUpdatedBy("user");

            this.doctorRepository.save(Doctor);

            return this.doctorMapper.toDoctorResponseDTO(Doctor);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving doctor", e);
        }
    }

    public Boolean deleteDoctor(Long id) {
        try {
            Optional<Doctor> optionalDoctor = this.doctorRepository.findById(id);
            if (optionalDoctor.isEmpty()) return null;
            this.doctorRepository.delete(optionalDoctor.get());
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting doctor", e);
        }
    }

    public List<DoctorResponseDTO> findAllDoctorsByNameAndLastName(String name, String lastName) {
        try {
            return this.doctorRepository.findAllByNameAndLastNameContainingIgnoreCase(name, lastName)
                    .stream()
                    .map(this.doctorMapper::toDoctorResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

    public List<DoctorResponseDTO> findAllDoctorsByName(String name) {
        try {
            return this.doctorRepository.findAllByNameContainingIgnoreCase(name)
                    .stream()
                    .map(this.doctorMapper::toDoctorResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

    public List<DoctorResponseDTO> findAllDoctorsByLastName(String lastName) {
        try {
            return this.doctorRepository.findAllByLastNameContainingIgnoreCase(lastName)
                    .stream()
                    .map(this.doctorMapper::toDoctorResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }

    public List<DoctorResponseDTO> findAllByCreatedAtBetween(LocalDate startDate, LocalDate endDate) {
        try {
            return this.doctorRepository.findAllByCreatedAtBetween(startDate, endDate)
                    .stream()
                    .map(this.doctorMapper::toDoctorResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting hospital", e);
        }
    }
}
