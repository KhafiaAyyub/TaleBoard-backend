package com.boot.taleboard_backend.repository;


import com.boot.taleboard_backend.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId); // Get all posts by a specific user
}
