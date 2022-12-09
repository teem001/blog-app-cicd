package com.olayinka.blogapp.services.servicImplementations;

import com.olayinka.blogapp.entities.Admin;
import com.olayinka.blogapp.pojos.AdminDto;
import com.olayinka.blogapp.pojos.LoginDto;
import com.olayinka.blogapp.repositories.AdminRepository;
import com.olayinka.blogapp.services.AdminService;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;


@Service
@Getter
@Setter

@AllArgsConstructor
public class AdminServiceImplementation implements AdminService {
    private  final AdminRepository adminRepository;
    private HttpSession session;

//    private  final PostService postService;





    @Override
    public AdminDto createAdmin(AdminDto adminDto){
        Admin admin = new Admin();

        System.out.println(adminDto);
        if (adminRepository.existsAdminByEmail(adminDto.getEmail())) {
            throw  new RuntimeException("Admin Exist");

        }
        else {
            BeanUtils.copyProperties(adminDto, admin);
            System.out.println(admin);
            Admin saved = adminRepository.save(admin);

            BeanUtils.copyProperties(saved,adminDto);
            return adminDto;
        }
    }


    @Override
    public String adminLogin(LoginDto loginDto){

        if(adminRepository.existsAdminByEmail(loginDto.getEmail()) &
                adminRepository.findByEmail(loginDto.getEmail())
                        .getPassword().equals(loginDto.getPassword())){
            Admin admin = adminRepository.findByEmail(loginDto.getEmail());
            session.setAttribute("adminID",admin.getId() );
            return "login successful";
        }
        else{
            return "Bad credential";
        }

    }

}
