package com.alaminkarno.blogxpress.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotEmpty(message = "About can not be empty or null")
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
