package com.example.demo.notes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;




@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class NoteController {
    @Autowired
    private final NoteRepository noteRepository;
    public NoteController(NoteRepository noterepository) {
        this.noteRepository = noterepository;
    }

    @GetMapping("{user_id}/notes")
    public List getAllPostsByUser(@PathVariable String user_id) {
        List<Object[]> posts = noteRepository.selectNotesByUserId(user_id);
        List response = posts.stream()
                .map(note -> new NoteResponse(note[0], note[1], note[2]))
                .collect(Collectors.toList());
        return response;
    }

    @GetMapping("/posts/{post_id}")
    public NoteResponse getNoteById(@PathVariable String note_id) {
        Object[] note = noteRepository.selectNoteById(Integer.parseInt(note_id)).get(0);
        NoteResponse response = new NoteResponse(note[0], note[1], note[2]);
        return response;
    }

    @PatchMapping("/posts/{post_id}")
    public NoteResponse updateNote(@PathVariable String note_id, @RequestBody Note postBody) {
        noteRepository.updateNote( postBody.getContent(), Integer.parseInt(note_id));
        Object[] note = noteRepository.selectNoteById(Integer.parseInt(note_id)).get(0);
        NoteResponse response = new NoteResponse(note[0], note[1], note[2]);
        return response;
    }

    @DeleteMapping("/posts/{post_id}")
    public NoteResponse deleteNote(@PathVariable String note_id) {
        Object[] note = noteRepository.selectNoteById(Integer.parseInt(note_id)).get(0);
        NoteResponse response = new NoteResponse(note[0], note[1], note[2]);
        noteRepository.deleteById(Long.parseLong(note_id));
        return response;
    }

    @PostMapping("/notes")
    public Note createPost(@RequestBody HashMap<String, String> info) {
        Note note = new Note();
        note.setContent(info.get("content"));
        return noteRepository.save(note);
    }

}