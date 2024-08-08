package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.config.AppConstants;
import com.alaminkarno.blogxpress.payloads.ApiResponse;
import com.alaminkarno.blogxpress.payloads.FileResponse;
import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.payloads.PostResponse;
import com.alaminkarno.blogxpress.services.FileService;
import com.alaminkarno.blogxpress.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

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
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "orderBy",defaultValue = AppConstants.ORDER_BY, required = false) String orderBy){

        PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy,orderBy);
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

    // GET: GET POSTS BY SEARCH KEYWORD
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> getPostsByKeyword(@PathVariable String keywords){
        List<PostDto> postDtoList = this.postService.searchPost(keywords);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    // POST: UPLOAD POST IMAGE
    @PostMapping("/posts/images/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);

        String fileName = this.fileService.uploadImage(path,image);
        postDto.setImage(fileName);

        PostDto updatedPost = this.postService.updatePost(postDto,postId);

        return  new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }

    // GET: POST IMAGE RESOURCE
    @GetMapping(value = "/posts/images/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = this.fileService.getResource(path,imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(inputStream,response.getOutputStream());
    }


}
