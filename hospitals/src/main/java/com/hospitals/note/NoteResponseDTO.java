package com.hospitals.note;

import java.time.LocalDate;

public record NoteResponseDTO(
        Long id,
        String description,
        LocalDate noteDate,
        LocalDate createdAt,
        LocalDate updatedAt,
        String createdBy,
        String updatedBy,
        Long patientId,
        Long doctorId
) {
}
