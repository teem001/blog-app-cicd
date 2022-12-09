package com.olayinka.blogapp.services.servicImplementations;

import com.olayinka.blogapp.entities.Comment;
import com.olayinka.blogapp.entities.Post;
import com.olayinka.blogapp.pojos.CommentDto;
import com.olayinka.blogapp.repositories.CommentRepository;
import com.olayinka.blogapp.repositories.PostRepository;
import com.olayinka.blogapp.services.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentServiceImplementation implements CommentService {
    private  final CommentRepository commentRepository;
    private  final PostRepository postRepository;


    @Override
    public String createComment(CommentDto commentDto, Long id, Long userId) {
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = new Post();
        Comment comment = new Comment();
        if(postOptional.isPresent()){
            post = postOptional.get();
            if (post.getAdmin().getId().equals(userId)){
                BeanUtils.copyProperties(commentDto,comment);

                commentRepository.save(comment);
                return "Comment sucessfull created";
            }
        }

        return "the operation fail";
    }
    @Override
    public String updateComment(CommentDto commentDto, Long commentId){
        Optional<Comment> commentOptional= commentRepository.findById(commentId);
        if(commentOptional.isPresent()){
            Comment comment = commentOptional.get();
            if(commentDto!=null)
                BeanUtils.copyProperties(commentDto,comment);
            commentRepository.save(comment);
            return "comment updated successfully ";

        }
        return "Operation not possible";
    }

    public List<Comment> getAllComment(){
        return commentRepository.findAll();

    }

    public List<Comment> getAllCommentForAPost(Long id){
        return commentRepository.findAllByPost_Id(id);
    }
    @Override
    public String deleteComment(Long commentId){
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if(commentOptional.isPresent()){
            Comment comment = commentOptional.get();
            commentRepository.delete(comment);
            return "comment deleted successfully";

        }
        return "operation is unavailable";
    }

}
