package com.olayinka.blogapp.repositories;

import com.olayinka.blogapp.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,Long> {
    boolean existsAdminByEmail(String email);
    Admin findByEmail(String email);
    Optional<Admin> findAdminByEmail(String email);

//    String findBy
}
