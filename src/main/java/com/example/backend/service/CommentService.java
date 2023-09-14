package com.example.backend.service;

import com.example.backend.model.Comment;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    Comment addComment(Comment comment);

    Page<Comment> getAllComments(Integer page, Integer limit);

    void deleteComment(Long id);

    Comment replyToComment(Long id, Comment comment);

    List<Comment> getRepliedComments(Long id);
}
