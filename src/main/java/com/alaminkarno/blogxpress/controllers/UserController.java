package com.alaminkarno.blogxpress.controllers;

import com.alaminkarno.blogxpress.payloads.UserDto;
import com.alaminkarno.blogxpress.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // POST: Create User
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto newUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }

    // PUT: Update User
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto){
        UserDto updateUser = this.userService.updateUser(userDto,1);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    // DELETE: Delete User

    // GET: Get User Details
}
