package com.example.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String author;

    @Column
    private String message;

    @Column
    private boolean reply = false;

    @Column
    private Long replyId;

    @Column
    private Long parentCommentId;
}
