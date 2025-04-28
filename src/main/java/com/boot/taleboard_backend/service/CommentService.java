package com.boot.taleboard_backend.service;

import com.boot.taleboard_backend.entity.Comment;
import java.util.List;


public interface CommentService {
    Comment addComment(Long postId, Long userId, String content);
    List<Comment> getCommentsByPost(Long postId);
}