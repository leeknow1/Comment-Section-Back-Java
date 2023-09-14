package com.example.backend.repository;

import com.example.backend.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.reply = false ")
    Page<Comment> findAllNotReply(PageRequest id);
    @Query("select c from Comment c where c.parentCommentId = ?1 order by c.id asc")
    List<Comment> findAllReplied(Long id);
}
