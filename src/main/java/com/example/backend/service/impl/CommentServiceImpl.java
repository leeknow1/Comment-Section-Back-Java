package com.example.backend.service.impl;

import com.example.backend.model.Comment;
import com.example.backend.repository.CommentRepository;
import com.example.backend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public Comment addComment(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Page<Comment> getAllComments(Integer page, Integer limit) {
        return repository.findAllNotReply(PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, "id")));
    }

    @Override
    public void deleteComment(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Comment replyToComment(Long id, Comment comment) {
        Comment replyingComment = repository.findById(id).orElseThrow();
        Comment newComment = new Comment();
        newComment.setReply(true);
        newComment.setReplyId(id);
        newComment.setAuthor(comment.getAuthor());
        newComment.setMessage("@" + replyingComment.getAuthor() + ", " + comment.getMessage());

        if (replyingComment.getParentCommentId() != null)
            newComment.setParentCommentId(replyingComment.getParentCommentId());
        else
            newComment.setParentCommentId(replyingComment.getId());
        return repository.save(newComment);
    }

    @Override
    public List<Comment> getRepliedComments(Long id) {
        return repository.findAllReplied(id);
    }
}
