package com.hospitals.patient;

import com.hospitals.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByName(String name);

    List<Patient> findAllByNameAndLastNameContainingIgnoreCase(String name, String lastName);

    List<Patient> findAllByNameContainingIgnoreCase(String name);

    List<Patient> findAllByLastNameContainingIgnoreCase(String name);

    List<Patient> findAllByCreatedAtBetween(LocalDate startDate, LocalDate endDate);

    List<Patient> findAllByBirthDateBetween(LocalDate startDate, LocalDate endDate);

}
