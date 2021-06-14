package com.notes.disqo.web.rest;

import com.notes.disqo.dto.NoteDTO;
import com.notes.disqo.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/notes")
public class NoteController {

    private final Logger log = LoggerFactory.getLogger(NoteController.class);

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody NoteDTO noteDTO) {
        log.debug("REST request to create Note: {}", noteDTO);
        return new ResponseEntity<>(noteService.createNote(noteDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable(value = "id", required = false) final Long id,
                                        @RequestBody NoteDTO noteDTO) {
        log.debug("REST request to update Note: {} {}", id, noteDTO);
        return new ResponseEntity<>(noteService.updateNote(noteDTO, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNote(@PathVariable(value = "id", required = false) final Long id) {
        log.debug("REST request to get Note: {}", id);
        return new ResponseEntity<>(noteService.getNote(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllNote(Pageable pageable) {
        log.debug("REST request to get User All Notes");
        return new ResponseEntity<>(noteService.getUserAllNotes(pageable), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id", required = false) final Long id) {
        noteService.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
