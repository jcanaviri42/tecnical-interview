package com.hospitals.note;

import org.springframework.stereotype.Service;

@Service
public class NoteMapper {

    public NoteResponseDTO toNoteResponseDTO(Note note) {
        return new NoteResponseDTO(
                note.getId(),
                note.getDescription(),
                note.getNoteDate(),
                note.getCreatedAt(),
                note.getUpdatedAt(),
                note.getCreatedBy(),
                note.getUpdatedBy(),
                note.getPatient().getId(),
                note.getDoctor().getId()
        );
    }

}
