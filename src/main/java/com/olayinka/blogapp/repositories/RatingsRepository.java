package com.olayinka.blogapp.repositories;

import com.olayinka.blogapp.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingsRepository extends JpaRepository<Rating, Long> {
    Rating findByPost_Id( Long postId);

}
