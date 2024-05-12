package com.alaminkarno.blogxpress.repositories;

import com.alaminkarno.blogxpress.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {



}
