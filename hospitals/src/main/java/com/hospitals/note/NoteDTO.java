package com.hospitals.note;

import java.time.LocalDate;

public record NoteDTO(
        String description,
        LocalDate noteDate,
        Long patientId,
        Long doctorId
) {
}
