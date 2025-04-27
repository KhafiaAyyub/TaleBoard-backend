package com.boot.taleboard_backend.service.impl;

import com.boot.taleboard_backend.dto.PostRequest;
import com.boot.taleboard_backend.entity.Post;
import com.boot.taleboard_backend.entity.User;
import com.boot.taleboard_backend.repository.PostRepository;
import com.boot.taleboard_backend.repository.UserRepository;
import com.boot.taleboard_backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private UserRepository userRepository; 
	
    @Autowired
    private PostRepository postRepository;

    @Override
    public void createPost(PostRequest postRequest) {
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        System.out.println("Saving post: " + post.getTitle());  // Log message

        // 👇 Suppose you are getting the current logged-in user's email
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(currentUserEmail)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + currentUserEmail));

        post.setUser(user); // 👈 setting the user to the post!
        postRepository.save(post);  // Save the post to the database
        System.out.println("Post saved successfully with user ID: " + user.getId());

    }
}
