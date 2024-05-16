package com.alaminkarno.blogxpress.payloads;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.Comment;
import com.alaminkarno.blogxpress.entities.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer id;
    @NotEmpty(message = "Title can not be empty or null")
    private String title;
    @NotEmpty(message = "Title can not be empty or null")
    private String content;
    private String image;
    private Date created;
    private Date updated;
    private CategoryDto category;
    private UserDto user;
    private Set<CommentDto> comments = new HashSet<>();

}
