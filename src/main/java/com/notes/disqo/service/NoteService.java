package com.notes.disqo.service;

import com.notes.disqo.domain.Note;
import com.notes.disqo.domain.User;
import com.notes.disqo.dto.NoteDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    NoteDTO createNote(NoteDTO noteDTO);

    NoteDTO updateNote(NoteDTO noteDTO, Long id);

    NoteDTO getNote(Long id);

    List<NoteDTO> getUserAllNotes(Pageable pageable);

    void deleteNote(Long id);

    Note getNoteById(Long id);

    void checkIsNoteOwner(User noteUser, User authUser);
}
