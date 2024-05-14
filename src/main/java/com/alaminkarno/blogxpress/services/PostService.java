package com.alaminkarno.blogxpress.services;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.Post;
import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> searchPost(String keyword);

}
