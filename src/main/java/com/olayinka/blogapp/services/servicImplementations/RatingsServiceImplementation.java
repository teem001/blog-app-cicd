package com.olayinka.blogapp.services.servicImplementations;

import com.olayinka.blogapp.entities.Post;
import com.olayinka.blogapp.entities.Rating;
import com.olayinka.blogapp.repositories.PostRepository;
import com.olayinka.blogapp.repositories.RatingsRepository;
import com.olayinka.blogapp.services.RatingsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingsServiceImplementation implements RatingsService {
    private final RatingsRepository ratingsRepository;
    private final PostRepository postRepository;


    @Override
    public void fixRate(Integer star, Long postId){
        Rating rating;
        Post post = null;
      Optional<Post> postOptional = postRepository.findById(postId);

      if(postOptional.isPresent()){
          post= postOptional.get();
          rating =ratingsRepository.findByPost_Id(postId);
          BeanUtils.copyProperties(post, rating);
          switch (star) {
              case 1 -> rating.setNumberOfOneStar(rating.getNumberOfOneStar() + 1);
              case 2 -> rating.setNumberOfTwoStar(rating.getNumberOfTwoStar() + 1);
              case 3 -> rating.setNumberOFThreeStar(rating.getNumberOFThreeStar() + 1);
              case 4 -> rating.setNumberOfFourStar(rating.getNumberOfFourStar() + 1);
              case 5 -> rating.setNumberOfFiveStar(rating.getNumberOfFiveStar() + 1);
              default -> throw new RuntimeException("Unknown Rating Value");
          }


      }



    }
}
