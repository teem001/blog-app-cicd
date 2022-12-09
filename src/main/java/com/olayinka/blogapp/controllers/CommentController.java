package com.olayinka.blogapp.controllers;

import com.olayinka.blogapp.pojos.CommentDto;
import com.olayinka.blogapp.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor

@RequestMapping("/comment")
public class CommentController {

  private  final   CommentService commentService;

  @PostMapping("/comment/{postId}/{adminId}")
    public String createComment(@RequestBody CommentDto commentDto, @PathVariable Long postId, @PathVariable Long adminId){
      return(commentService.createComment(commentDto, postId,adminId));
  }

  @PutMapping("/update/{commentId}")
  public  String updateComment(@RequestBody CommentDto commentDto, @PathVariable Long commentId){
    return commentService.updateComment(commentDto,commentId);
  }


  @DeleteMapping("/delete/{commentId}")
  public  String deleteComment(@PathVariable Long commentId){
    return commentService.deleteComment(commentId);
  }




}
