package com.olayinka.blogapp.pojos;

import com.olayinka.blogapp.enums.Ratings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class CommentDto {
    private String name;
    private String nameUser;
    private Ratings ratings;
}
