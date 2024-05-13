package com.alaminkarno.blogxpress.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;

    @NotEmpty(message = "Name can not be empty or null")
    private String name;

    @Email(message = "Email address is not valid!")
    private String email;

    @NotEmpty(message = "Password can not be empty or null")
    @Size(min = 6,message = "Password length must be at least 6 digit")
    private String password;

    @NotEmpty(message = "About can not be empty or null")
    private String about;

}
