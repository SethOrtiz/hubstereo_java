package com.example.demo.notes;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String user_id;

    @Column(length=10485760)
    private String content;
}