package com.alaminkarno.blogxpress.repositories;

import com.alaminkarno.blogxpress.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
