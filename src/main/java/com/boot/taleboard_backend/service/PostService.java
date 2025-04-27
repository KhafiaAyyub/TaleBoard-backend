package com.boot.taleboard_backend.service;

import com.boot.taleboard_backend.dto.PostRequest;

public interface PostService {
    void createPost(PostRequest postRequest);
}
