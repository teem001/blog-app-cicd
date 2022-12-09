package com.olayinka.blogapp.respones;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class PostResponse <T>{
    private  String message;
    private HttpStatus status;
    private T  data;
}
