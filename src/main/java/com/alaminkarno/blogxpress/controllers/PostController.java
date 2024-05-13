package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.payloads.UserDto;
import com.alaminkarno.blogxpress.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    // POST: CREATE POST
    @PostMapping("/user/{userId}/category/{categoryId}/posts/")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto newPostDto = this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<>(newPostDto, HttpStatus.CREATED);
    }
}
