package com.olayinka.blogapp.pojos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
