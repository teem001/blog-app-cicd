package com.olayinka.blogapp.services;

import com.olayinka.blogapp.pojos.CommentDto;

public interface CommentService  {
    public String createComment(CommentDto commentDto, Long id, Long userId);
    public String updateComment(CommentDto commentDto, Long commentId);
    public String deleteComment(Long commentId);
}
