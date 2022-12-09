package com.olayinka.blogapp.pojos;

import lombok.Data;

@Data
public class AdminRequest {
    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
