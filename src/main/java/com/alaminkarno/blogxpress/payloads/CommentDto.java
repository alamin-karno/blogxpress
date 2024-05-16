package com.alaminkarno.blogxpress.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private int id;

    @NotEmpty(message = "Comment can not be empty or null")
    private String content;

    private UserDto user;
}
