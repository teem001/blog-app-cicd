package com.olayinka.blogapp.controllers;

import com.olayinka.blogapp.pojos.AdminDto;
import com.olayinka.blogapp.pojos.AdminRequest;
import com.olayinka.blogapp.pojos.LoginDto;
import com.olayinka.blogapp.respones.AdminResponse;
import com.olayinka.blogapp.respones.ApiResponse;
import com.olayinka.blogapp.respones.ResponseManager;
import com.olayinka.blogapp.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/sign-up")
    public ResponseEntity <ApiResponse<AdminResponse>> createAdmin(@RequestBody AdminRequest adminRequest){
        AdminDto adminDto = new AdminDto();
        AdminResponse adminResponse = new AdminResponse();
        BeanUtils.copyProperties(adminRequest,adminDto);

        BeanUtils.copyProperties(adminService.createAdmin(adminDto),adminResponse);

        return new ResponseEntity<>(new  ResponseManager<AdminResponse>().success(HttpStatus.CREATED,adminResponse),HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto){
        return adminService.adminLogin(loginDto);
    }

}
