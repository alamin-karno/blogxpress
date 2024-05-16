package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.ApiResponse;
import com.alaminkarno.blogxpress.payloads.CommentDto;
import com.alaminkarno.blogxpress.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // POST: CREATE COMMENT
    @PostMapping("users/{userId}/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto, @PathVariable Integer userId, @PathVariable Integer postId) {
        CommentDto createdComment = this.commentService.createComment(commentDto,postId,userId);

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // DELETE: DELETE COMMENT
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully!",true), HttpStatus.OK);
    }

}
