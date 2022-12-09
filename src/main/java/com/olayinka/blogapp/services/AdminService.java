package com.olayinka.blogapp.services;

import com.olayinka.blogapp.pojos.AdminDto;
import com.olayinka.blogapp.pojos.LoginDto;


public interface AdminService {
    public AdminDto createAdmin(AdminDto adminDto);
    public String adminLogin(LoginDto loginDto);


}
