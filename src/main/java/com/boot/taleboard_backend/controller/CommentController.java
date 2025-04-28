package com.boot.taleboard_backend.controller;


import com.boot.taleboard_backend.entity.Comment;
import com.boot.taleboard_backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // Add comment to a post
    @PostMapping("/add/{postId}/{userId}")
    public ResponseEntity<Comment> addComment(
            @PathVariable Long postId,
            @PathVariable Long userId,
            @RequestParam String content) {
        Comment comment = commentService.addComment(postId, userId, content);
        return ResponseEntity.ok(comment);
    }

    // Get comments for a specific post
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(comments);
    }
}
