package com.notes.disqo.repository;

import com.notes.disqo.domain.Note;
import com.notes.disqo.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUser(User user, Pageable pageable);
}
