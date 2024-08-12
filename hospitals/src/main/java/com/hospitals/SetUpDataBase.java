package com.hospitals;

import com.hospitals.speciality.Speciality;
import com.hospitals.speciality.SpecialityRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@SuppressWarnings("unused")
public class SetUpDataBase implements CommandLineRunner {

    public final SpecialityRepository specialityRepository;

    public SetUpDataBase(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        List<Speciality> all = this.specialityRepository.findAll();
        if (all.isEmpty()) populateSpecialities();
    }

    private void populateSpecialities() {
        List<String> specialities = new ArrayList<>();
        specialities.add("Cardiology");
        specialities.add("Orthopedics");
        specialities.add("Neurology");
        specialities.add("Dermatology");
        specialities.add("Psychiatry");

        List<Speciality> specialityList = new ArrayList<>();
        for (String speciality : specialities) {
            Speciality currentSpeciality = new Speciality();
            currentSpeciality.setName(speciality);
            currentSpeciality.setDescription(speciality + " services");
            currentSpeciality.setCreatedAt(getRandomLocalDate());
            currentSpeciality.setCreatedBy("system");
            specialityList.add(currentSpeciality);
        }

        this.specialityRepository.saveAll(specialityList);
    }

    private LocalDate getRandomLocalDate() {
        var random = new Random();

        int year = random.nextInt(1990, 2000);
        int month = random.nextInt(1, 13);
        int dayOfMonth = random.nextInt(1, 28);
        return LocalDate.of(year, month, dayOfMonth);
    }
}
