package com.example.backend.controller;

import com.example.backend.model.Comment;
import com.example.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080", exposedHeaders = "x-total-count")
public class CommentController {

    private final CommentService service;

    @PostMapping("/comment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addComment(comment));
    }

    @PostMapping("/comment/{id}")
    public ResponseEntity<Comment> replyToComment(@PathVariable Long id,
                                                  @RequestBody Comment comment) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.replyToComment(id, comment));
    }

    @GetMapping("/comment")
    public ResponseEntity<List<Comment>> getAllComments(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        Page<Comment> comments = service.getAllComments(page, limit);
        return ResponseEntity
                .ok()
                .header("x-total-count", String.valueOf(comments.getTotalElements()))
                .body(comments.getContent());
    }

    @GetMapping("/comment/{id}/replies")
    public ResponseEntity<List<Comment>> getRepliedComments(@PathVariable Long id) {
        List<Comment> comments = service.getRepliedComments(id);
        return ResponseEntity
                .ok()
                .body(comments);
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        service.deleteComment(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
