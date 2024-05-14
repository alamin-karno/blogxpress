package com.alaminkarno.blogxpress.services.impl;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.Post;
import com.alaminkarno.blogxpress.entities.User;
import com.alaminkarno.blogxpress.exceptions.ResourceNotFoundException;
import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.payloads.PostResponse;
import com.alaminkarno.blogxpress.repositories.CategoryRepository;
import com.alaminkarno.blogxpress.repositories.PostRepository;
import com.alaminkarno.blogxpress.repositories.UserRepository;
import com.alaminkarno.blogxpress.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        post.setContent(postDto.getContent());
        post.setImage(postDto.getImage());
        post.setUpdated(new Date());

        Post updatedPost = this.postRepository.save(post);

        return this.postToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId));

        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));

        Page<Post> pagePost = this.postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();

        List<PostDto> postDtoList = posts.stream().map(this::postToDto).toList();

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId));

        return this.postToDto(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category","Id",categoryId));

        List<Post> posts = this.postRepository.findByCategory(category);

        return posts.stream().map(this::postToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));

        List<Post> posts = this.postRepository.findByUser(user);

        return posts.stream().map(this::postToDto).collect(Collectors.toList());
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
