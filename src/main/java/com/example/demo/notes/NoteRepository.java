package com.example.demo.notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.ArrayList;
import java.util.List;

public interface NoteRepository extends JpaRepository <Note, Long> {

    @Query(value = "SELECT notes.id, notes.content, users.userId, FROM notes WHERE notes.user_id = ?1", nativeQuery = true)
    ArrayList<Object[]> selectNotesByUserId(String user_id);

    @Query(value = "SELECT notes.id, notes.content, users.userId, FROM notes WHERE posts.id = ?1", nativeQuery = true)
    ArrayList<Object[]> selectNoteById(int id);
    Note findById(long id);

    @Query(value = "UPDATE notes SET content = ?1, WHERE id = ?2 RETURNING *", nativeQuery = true)
    Note updateNote(String content, int id);
}