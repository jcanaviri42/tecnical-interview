package com.hospitals.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    boolean existsByName(String name);

    List<Doctor> findAllByNameAndLastNameContainingIgnoreCase(String name, String lastName);

    List<Doctor> findAllByNameContainingIgnoreCase(String name);

    List<Doctor> findAllByLastNameContainingIgnoreCase(String name);

    List<Doctor> findAllByBirthDateBetween(LocalDate startDate, LocalDate endDate);

}
