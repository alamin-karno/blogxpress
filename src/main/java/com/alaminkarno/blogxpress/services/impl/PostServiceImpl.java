package com.alaminkarno.blogxpress.services.impl;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.Post;
import com.alaminkarno.blogxpress.entities.User;
import com.alaminkarno.blogxpress.exceptions.ResourceNotFoundException;
import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.payloads.UserDto;
import com.alaminkarno.blogxpress.repositories.CategoryRepository;
import com.alaminkarno.blogxpress.repositories.PostRepository;
import com.alaminkarno.blogxpress.repositories.UserRepository;
import com.alaminkarno.blogxpress.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;



    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId));

        Post post =  this.dtoToPost(postDto);

        post.setImage("default.png");
        post.setCreated(new Date());
        post.setUpdated(new Date());
        post.setUser(user);
        post.setCategory(category);

       Post savedPost = this.postRepository.save(post);

       return this.postToDto(savedPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId));

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getTitle());
        post.setImage(postDto.getImage());
        post.setUpdated(new Date());
        post.setUser(this.modelMapper.map(postDto.getUser(), User.class));
        post.setCategory(this.modelMapper.map(postDto.getCategory(),Category.class));

        Post updatedPost = this.postRepository.save(post);

        return this.postToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {

    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }

    public Post dtoToPost(PostDto postDto){
        return this.modelMapper.map(postDto,Post.class);
    }

    public PostDto postToDto(Post post){
        return this.modelMapper.map(post,PostDto.class);
    }
}
