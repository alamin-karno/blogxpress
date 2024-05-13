package com.alaminkarno.blogxpress.repositories;

import com.alaminkarno.blogxpress.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
