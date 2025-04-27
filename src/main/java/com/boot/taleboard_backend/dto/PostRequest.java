package com.boot.taleboard_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PostRequest {
    private String title;
    private String content;

}
