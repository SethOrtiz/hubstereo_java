package com.example.demo.notes;
import lombok.Data;
import java.math.BigInteger;

@Data
public class NoteResponse {

    private BigInteger id;
    private String user_id;
    private String content;

    public NoteResponse(Object id, Object user_id, Object content) {
        this.id = (BigInteger) id;
        this.user_id = (String) user_id;
        this.content = (String) content;
    }
}