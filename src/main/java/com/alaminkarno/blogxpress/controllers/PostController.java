package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.ApiResponse;
import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.payloads.PostResponse;
import com.alaminkarno.blogxpress.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // PUT: UPDATE POST
    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId){

        PostDto updatedPostDto = this.postService.updatePost(postDto,postId);

        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    // DELETE: DELETE POST
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully",true), HttpStatus.OK);
    }

    // GET: GET ALL POSTS
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy){

        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // GET: GET POST BY ID
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    // GET: GET POSTS BY USER
    @GetMapping("/user/{userId}/posts/")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
       List<PostDto> postDtoList = this.postService.getPostsByUser(userId);
       return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    // GET: GET POSTS BY CATEGORY
    @GetMapping("/category/{categoryId}/posts/")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList = this.postService.getPostsByCategory(categoryId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}
