package com.hospitals.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    boolean existsByName(String name);

    List<Patient> findAllByNameAndLastNameContainingIgnoreCase(String name, String lastName);

    List<Patient> findAllByNameContainingIgnoreCase(String name);

    List<Patient> findAllByLastNameContainingIgnoreCase(String name);

}
