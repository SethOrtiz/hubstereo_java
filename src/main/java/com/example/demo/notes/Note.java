package com.example.demo.notes;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String content;
    @Column
    private String user_id;
}