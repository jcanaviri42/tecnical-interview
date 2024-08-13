package com.hospitals.note;

import com.hospitals.doctor.Doctor;
import com.hospitals.doctor.DoctorRepository;
import com.hospitals.patient.Patient;
import com.hospitals.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final NoteMapper noteMapper;

    public NoteService(
            NoteRepository noteRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository,
            NoteMapper noteMapper) {

        this.noteRepository = noteRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.noteMapper = noteMapper;
    }

    public NoteResponseDTO saveNote(NoteDTO dto) {

        Optional<Patient> patient = this.patientRepository.findById(dto.patientId());
        if (patient.isEmpty())
            return null;
        Optional<Doctor> doctor = this.doctorRepository.findById(dto.doctorId());
        if (doctor.isEmpty())
            return null;

        try {
            Note note = Note.builder()
                    .description(dto.description())
                    .noteDate(dto.noteDate())
                    .createdAt(LocalDate.now())
                    .createdBy("user")
                    .patient(patient.get())
                    .doctor(doctor.get())
                    .build();

            this.noteRepository.save(note);
            return this.noteMapper.toNoteResponseDTO(note);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving Note", e);
        }
    }

    public List<NoteResponseDTO> findAllNotes() {
        return this.noteRepository.findAll()
                .stream()
                .map(this.noteMapper::toNoteResponseDTO)
                .collect(Collectors.toList());
    }

    public NoteResponseDTO findNoteById(Long id) {
        try {
            Optional<Note> optionalNote = this.noteRepository.findById(id);
            return optionalNote.map(this.noteMapper::toNoteResponseDTO).orElse(null);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving Note", e);
        }
    }

    public NoteResponseDTO updateNote(Long id, NoteDTO dto) {
        try {
            Optional<Note> optionalNote = this.noteRepository.findById(id);
            if (optionalNote.isEmpty()) return null;

            Note note = optionalNote.get();

            if (dto.description() != null) note.setDescription(dto.description());
            if (dto.noteDate() != null) note.setNoteDate(dto.noteDate());

            note.setUpdatedAt(LocalDate.now());
            // TODO: Get the current user
            note.setUpdatedBy("user");

            this.noteRepository.save(note);

            return this.noteMapper.toNoteResponseDTO(note);
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error saving Note", e);
        }
    }

    public Boolean deleteNote(Long id) {
        try {
            Optional<Note> optionalNote = this.noteRepository.findById(id);
            if (optionalNote.isEmpty()) return null;
            this.noteRepository.delete(optionalNote.get());
            return true;
        } catch (Exception e) {
            System.out.println("e = " + e);
            throw new RuntimeException("Unexpected error deleting Note", e);
        }
    }

}
