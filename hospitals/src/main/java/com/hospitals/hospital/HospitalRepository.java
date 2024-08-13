package com.hospitals.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    boolean existsByName(String name);

    List<Hospital> findAllByNameContainingIgnoreCase(String q);

    List<Hospital> findAllByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
}
