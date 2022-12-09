package com.olayinka.blogapp.controllers;

import com.olayinka.blogapp.entities.Post;
import com.olayinka.blogapp.enums.Ratings;
import com.olayinka.blogapp.pojos.PostDto;
import com.olayinka.blogapp.pojos.RatingPojo;
import com.olayinka.blogapp.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@AllArgsConstructor
public class PostController {
    PostService postService;


    @PostMapping("/create-post")
    public String createPost(@RequestBody PostDto postDto){
        return postService.createAPost(postDto);
    }

    @PutMapping("/udate-post/{adminId}/{postId}")
    public  String updatePost(@RequestBody PostDto postDto, @PathVariable Long adminId, @PathVariable Long postId){
        return postService.updatePost(adminId, postId, postDto);

    }
    @GetMapping("/read-all")
    public List<Post> getAllAdminPosts(){
        return postService.allAdminPost();
    }
    @DeleteMapping("/delete/{adminId}/{postId}")
    public String deleteAPost(@PathVariable Long adminId, @PathVariable Long postId){
        return postService.deleteAPost(adminId, postId);
    }
    @GetMapping("/get-all")
    public List<Post> getAllPost(){

        return postService.allPost();
    }

    @PostMapping("/rating/{postId}")
    public String rateAPost(@PathVariable Long postId, @RequestBody RatingPojo rate){
        Ratings ratings = Ratings.valueOf(rate.getRate());
        return postService.rateAPost(ratings,postId);
    }


 }
