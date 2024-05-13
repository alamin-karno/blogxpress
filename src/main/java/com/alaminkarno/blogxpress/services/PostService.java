package com.alaminkarno.blogxpress.services;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.Post;
import com.alaminkarno.blogxpress.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    List<PostDto> getAllPosts();

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

}
