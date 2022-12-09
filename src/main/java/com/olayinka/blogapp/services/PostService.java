package com.olayinka.blogapp.services;

import com.olayinka.blogapp.entities.Post;
import com.olayinka.blogapp.enums.Ratings;
import com.olayinka.blogapp.pojos.PostDto;

import java.util.List;

public interface PostService {
    List<Post> allPost();
    public String createAPost(PostDto postDto);
    List<Post> allAdminPost();
     String updatePost(Long adminId, Long postId, PostDto postDto);
    String deleteAPost(Long adminId, Long postId);
    public String rateAPost(Ratings ratings, Long id);

}
