package com.hospitals.note;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@SuppressWarnings("unused")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<?> saveNote(@RequestBody NoteDTO dto) {
        try {
            NoteResponseDTO savedNoteDTO = this.noteService.saveNote(dto);
            if (savedNoteDTO == null)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't create a note");
            return ResponseEntity.status(HttpStatus.CREATED).body(savedNoteDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping
    public List<NoteResponseDTO> findAllNotes() {
        return this.noteService.findAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findNoteById(@PathVariable Long id) {
        try {
            NoteResponseDTO noteDTO = this.noteService.findNoteById(id);
            if (noteDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found.");
            return ResponseEntity.status(HttpStatus.OK).body(noteDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody NoteDTO dto) {
        try {
            NoteResponseDTO noteDTO = this.noteService.updateNote(id, dto);
            if (noteDTO == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found.");
            return ResponseEntity.status(HttpStatus.OK).body(noteDTO);
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        try {
            Boolean hasBeenDeleted = this.noteService.deleteNote(id);
            if (hasBeenDeleted == null)
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found.");

            if (!hasBeenDeleted)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Couldn't delete the note.");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Note deleted.");
        } catch (Exception e) {
            System.out.println("e = " + e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
