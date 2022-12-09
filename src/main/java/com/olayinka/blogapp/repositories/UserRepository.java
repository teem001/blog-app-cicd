package com.olayinka.blogapp.repositories;

import com.olayinka.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
