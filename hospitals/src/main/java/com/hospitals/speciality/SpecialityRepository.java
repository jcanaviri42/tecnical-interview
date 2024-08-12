package com.hospitals.speciality;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

    boolean existsByName(String name);

}
