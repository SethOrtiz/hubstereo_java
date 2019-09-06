package com.example.demo.notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface NoteRepository extends JpaRepository <Note, Integer> {

    @Query(value = "SELECT * FROM notes ", nativeQuery = true)
    List<Object[]> selectAllNotes();

    @Query(value = "SELECT * FROM notes WHERE user_id = ?1 ", nativeQuery = true)
    List<Object[]> selectNotesByUserId(String user_id);

    Note findById(int id);

//    @Query(value = "UPDATE notes SET content = ?1, WHERE id = ?2, RETURNING *", nativeQuery = true)
//    Note updateNote(String content, int id);
}