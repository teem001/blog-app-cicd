package com.olayinka.blogapp.respones;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ResponseManager<T> {
    public ApiResponse<T> success(HttpStatus status, T data){
        return  new ApiResponse<T>("successfully", status.value(),data);

    }
    public ApiResponse<T> error(HttpStatus status, String message){
        return  new ApiResponse<T>(message, status.value(), null);

    }
}
