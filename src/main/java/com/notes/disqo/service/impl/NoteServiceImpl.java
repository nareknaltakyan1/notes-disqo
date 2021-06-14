package com.notes.disqo.service.impl;

import com.notes.disqo.domain.Note;
import com.notes.disqo.domain.User;
import com.notes.disqo.dto.NoteDTO;
import com.notes.disqo.exaption.DtoHasAnIdException;
import com.notes.disqo.exaption.NoteIsNotExistException;
import com.notes.disqo.exaption.UsersAreNotTheSameException;
import com.notes.disqo.mapper.NoteMapper;
import com.notes.disqo.repository.NoteRepository;
import com.notes.disqo.service.NoteService;
import com.notes.disqo.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteMapper noteMapper;

    private final NoteRepository noteRepository;

    private final UserService userService;

    public NoteServiceImpl(NoteMapper noteMapper, NoteRepository noteRepository, UserService userService) {
        this.noteMapper = noteMapper;
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    public NoteDTO createNote(NoteDTO noteDTO) {
        if (noteDTO.getId() != null) {
            throw new DtoHasAnIdException();
        }
        Note note = noteMapper.toEntity(noteDTO);
        note.setUser(userService.getUser());
        return noteMapper.toDTO(noteRepository.save(note));
    }

    public NoteDTO updateNote(NoteDTO noteDTO, Long id) {
        Note note = this.getNoteById(id);
        this.checkIsNoteOwner(note.getUser(), userService.getUser());
        note.setId(id);
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        return noteMapper.toDTO(noteRepository.save(note));
    }

    public NoteDTO getNote(Long id) {
        Note note = this.getNoteById(id);
        this.checkIsNoteOwner(note.getUser(), userService.getUser());
        return noteMapper.toDTO(note);
    }

    public List<NoteDTO> getUserAllNotes(Pageable pageable) {
        User user = userService.getUser();
        List<Note> notes = noteRepository.findAllByUser(user, pageable);
        return noteMapper.toDTO(notes);
    }

    public void deleteNote(Long id) {
        Note note = this.getNoteById(id);
        this.checkIsNoteOwner(note.getUser(), userService.getUser());
        noteRepository.delete(note);
    }

    public Note getNoteById(Long id) {
        Optional<Note> optNote = noteRepository.findById(id);
        if (!optNote.isPresent()) {
            throw new NoteIsNotExistException();
        }
        return optNote.get();
    }

    public void checkIsNoteOwner(User noteUser, User authUser) {
        if (!noteUser.equals(authUser)) {
            throw new UsersAreNotTheSameException();
        }
    }
}
