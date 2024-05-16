package com.alaminkarno.blogxpress.services;

import com.alaminkarno.blogxpress.payloads.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    void deleteCommentById(Integer commentId);
}
