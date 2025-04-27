package com.boot.taleboard_backend.repository;


import com.boot.taleboard_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId); // Get all comments for a post
}
