package com.boot.taleboard_backend.service.impl;


import com.boot.taleboard_backend.entity.Comment;
import com.boot.taleboard_backend.entity.Post;
import com.boot.taleboard_backend.entity.User;
import com.boot.taleboard_backend.repository.CommentRepository;
import com.boot.taleboard_backend.repository.PostRepository;
import com.boot.taleboard_backend.repository.UserRepository;
import com.boot.taleboard_backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Comment addComment(Long postId, Long userId, String content) {
        // Fetch Post and User from DB
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Create new comment
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPost(post);
        comment.setUser(user);

        // Save and return the comment
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
