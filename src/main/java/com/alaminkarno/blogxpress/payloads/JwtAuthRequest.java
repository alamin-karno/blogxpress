package com.alaminkarno.blogxpress.payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
    private String username;

    private String password;
}
