package com.notes.disqo.mapper;

import com.notes.disqo.domain.Note;
import com.notes.disqo.dto.NoteDTO;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NoteMapper {
    public Note toEntity(NoteDTO noteDTO) {
        if (noteDTO == null) {
            return null;
        }
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setContent(noteDTO.getContent());
        note.setTitle(noteDTO.getTitle());
        note.setCreateDate(noteDTO.getCreateDate());
        note.setLastModifyDate(noteDTO.getLastModifyDate());
        return note;
    }

    public NoteDTO toDTO(Note note) {
        if (note == null) {
            return null;
        }
        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setId(note.getId());
        noteDTO.setContent(note.getContent());
        noteDTO.setTitle(note.getTitle());
        noteDTO.setCreateDate(note.getCreateDate());
        noteDTO.setLastModifyDate(note.getLastModifyDate());
        return noteDTO;
    }

    public List<NoteDTO> toDTO(List<Note> notes){
        List<NoteDTO> dtos = new ArrayList<>(notes.size());
        for (Note note:notes) {
            dtos.add(this.toDTO(note));
        }
        return dtos;
    }
}
