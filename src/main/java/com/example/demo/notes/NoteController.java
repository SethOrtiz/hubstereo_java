package com.example.demo.notes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class NoteController {
    @Autowired
    private final NoteRepository repository;

    public NoteController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/notes")
    public List getAllNotes() {
        List<Object[]> notes = repository.selectAllNotes();
        return notes.stream()
                .map(note -> new NoteResponse(note[0], note[1], note[2]))
                .collect(Collectors.toList());
    }

    @GetMapping("users/{user_id}")
    public List getAllNotesByUser(@PathVariable String user_id) {
        List<Object[]> notes = repository.selectNotesByUserId(user_id);
        return notes.stream()
                .map(note -> new NoteResponse(note[0], note[1], note[2]))
                .collect(Collectors.toList());
    }

    @GetMapping("/notes/{note_id}")
    public Note getNoteById(@PathVariable String note_id) {
        return repository.findById(Integer.parseInt(note_id));
    }

    @PostMapping("/notes")
    public Note createNote(@RequestBody HashMap<String, String> info) {
        Note note = new Note();
        note.setUser_id(info.get("user_id"));
        note.setContent(info.get("content"));
        return repository.save(note);
    }

    @PatchMapping("/notes/{note_id}")
    public Note updateNoteById(@PathVariable String note_id, @RequestBody HashMap<String, String> info) {
        Note note = repository.findById(Integer.parseInt(note_id));
        note.setContent(info.get("content"));
        return repository.save(note);
    }

    @DeleteMapping("/notes/{note_id}")
    public Note deleteNote(@PathVariable String note_id) {
        Note note = repository.findById(Integer.parseInt(note_id));
        repository.deleteById(Integer.parseInt(note_id));
        return note;
    }
}