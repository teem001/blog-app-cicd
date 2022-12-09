package com.olayinka.blogapp.respones;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ApiResponse <T> {
    private  String message;
    private int status;
    private T  data;
}
