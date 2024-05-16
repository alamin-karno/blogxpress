package com.alaminkarno.blogxpress.services.impl;

import com.alaminkarno.blogxpress.entities.Comment;
import com.alaminkarno.blogxpress.entities.Post;
import com.alaminkarno.blogxpress.entities.User;
import com.alaminkarno.blogxpress.exceptions.ResourceNotFoundException;
import com.alaminkarno.blogxpress.payloads.CommentDto;
import com.alaminkarno.blogxpress.payloads.PostDto;
import com.alaminkarno.blogxpress.repositories.CommentRepository;
import com.alaminkarno.blogxpress.repositories.PostRepository;
import com.alaminkarno.blogxpress.repositories.UserRepository;
import com.alaminkarno.blogxpress.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {

        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","Id",postId));;

        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",postId));

        Comment comment = this.dtoToComment(commentDto);
        comment.setPost(post);
        comment.setUser(user);

        Comment createdComment = this.commentRepository.save(comment);

        return this.commentToDto(createdComment);
    }

    @Override
    public void deleteCommentById(Integer commentId) {
      Comment comment =  this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment","Id",commentId));

      this.commentRepository.delete(comment);
    }

    Comment dtoToComment(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }

    CommentDto commentToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }
}
