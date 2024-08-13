package com.hospitals;

import com.hospitals.doctor.Doctor;
import com.hospitals.doctor.DoctorRepository;
import com.hospitals.hospital.Hospital;
import com.hospitals.hospital.HospitalRepository;
import com.hospitals.note.Note;
import com.hospitals.note.NoteRepository;
import com.hospitals.patient.Patient;
import com.hospitals.patient.PatientRepository;
import com.hospitals.speciality.Speciality;
import com.hospitals.speciality.SpecialityRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@SuppressWarnings("unused")
public class SetUpDataBase implements CommandLineRunner {

    public final SpecialityRepository specialityRepository;
    public final HospitalRepository hospitalRepository;
    public final DoctorRepository doctorRepository;
    public final PatientRepository patientRepository;
    public final NoteRepository noteRepository;

    public SetUpDataBase(
            SpecialityRepository specialityRepository,
            HospitalRepository hospitalRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            NoteRepository noteRepository) {
        this.specialityRepository = specialityRepository;
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.noteRepository = noteRepository;
    }

    @Transactional
    @Override
    public void run(String... args) {
        List<Speciality> specialities = this.specialityRepository.findAll();
        if (specialities.isEmpty()) populateSpecialities();

        List<Hospital> hospitals = this.hospitalRepository.findAll();
        if (hospitals.isEmpty()) populateHospitals();

        List<Doctor> doctors = this.doctorRepository.findAll();
        if (doctors.isEmpty()) populateDoctors();

        List<Patient> patients = this.patientRepository.findAll();
        if (patients.isEmpty()) populatePatients();

        List<Note> notes = this.noteRepository.findAll();
        if (notes.isEmpty()) populateNotes();
    }

    private void populateNotes() {
        List<Note> notes = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Optional<Patient> patient = this.patientRepository.findById(random.nextLong(10));
            if (patient.isEmpty()) continue;

            Optional<Doctor> doctor = this.doctorRepository.findById(random.nextLong(10));
            if (doctor.isEmpty()) continue;

            Note note = Note.builder()
                    .description("Example Description")
                    .noteDate(getRandomLocalDate())
                    .createdAt(LocalDate.now())
                    .createdBy("user")
                    .patient(patient.get())
                    .doctor(doctor.get())
                    .build();

            notes.add(note);
        }

        this.noteRepository.saveAll(notes);
    }

    private void populatePatients() {
        List<String> firstNames = Arrays.asList("John", "Jane", "Michael", "Emily", "David", "Olivia");
        List<String> lastNames = Arrays.asList("Doe", "Smith", "Johnson", "Williams", "Brown", "Jones");
        Random random = new Random();

        List<Patient> patients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String randomName = firstNames.get(random.nextInt(firstNames.size()));
            String randomLastName = lastNames.get(random.nextInt(lastNames.size()));

            Optional<Hospital> hospital = this.hospitalRepository.findById(random.nextLong(5));
            if (hospital.isEmpty()) continue;

            Patient patient = Patient.builder()
                    .name(randomName)
                    .lastName(randomLastName)
                    .birthDate(getRandomLocalDate())
                    .address("street")
                    .createdAt(LocalDate.now())
                    .createdBy("system")
                    .hospital(hospital.get())
                    .build();

            patients.add(patient);
        }

        this.patientRepository.saveAll(patients);
    }

    private void populateDoctors() {
        List<String> firstNames = Arrays.asList("John", "Jane", "Michael", "Emily", "David", "Olivia");
        List<String> lastNames = Arrays.asList("Doe", "Smith", "Johnson", "Williams", "Brown", "Jones");
        Random random = new Random();

        List<Doctor> doctors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String randomName = firstNames.get(random.nextInt(firstNames.size()));
            String randomLastName = lastNames.get(random.nextInt(lastNames.size()));

            Optional<Hospital> hospital = this.hospitalRepository.findById(random.nextLong(5));
            if (hospital.isEmpty()) continue;

            Doctor doctor = Doctor.builder()
                    .name(randomName)
                    .lastName(randomLastName)
                    .createdAt(LocalDate.now())
                    .createdBy("system")
                    .hospital(hospital.get())
                    .build();
            doctors.add(doctor);
        }

        this.doctorRepository.saveAll(doctors);
    }

    private void populateHospitals() {
        List<String> hospitals = new ArrayList<>();
        hospitals.add("Hopewell Medical Center");
        hospitals.add("Greenwood Health System");
        hospitals.add("St. Luke's Hospital");
        hospitals.add("Valley Medical Center");
        hospitals.add("New Horizons Healthcare");

        List<Hospital> hospitalList = new ArrayList<>();
        for (String hospital : hospitals) {
            Hospital currentHospital = new Hospital();
            currentHospital.setName(hospital);
            currentHospital.setEmail(hospital + "@gmail.com");
            currentHospital.setPhone("555");
            currentHospital.setCreatedAt(getRandomLocalDate());
            currentHospital.setCreatedBy("system");
            hospitalList.add(currentHospital);
        }

        this.hospitalRepository.saveAll(hospitalList);
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
