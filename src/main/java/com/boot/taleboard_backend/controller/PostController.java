package com.boot.taleboard_backend.controller;

import com.boot.taleboard_backend.dto.PostRequest;
import com.boot.taleboard_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
        try {
            // Delegate the post creation to the service layer
            postService.createPost(postRequest);
            return new ResponseEntity<>("Post created successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            // Return a failure response if something goes wrong
            return new ResponseEntity<>("Failed to create post.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
