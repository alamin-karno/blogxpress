package com.alaminkarno.blogxpress.repositories;

import com.alaminkarno.blogxpress.entities.Category;
import com.alaminkarno.blogxpress.entities.Post;
import com.alaminkarno.blogxpress.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

}
