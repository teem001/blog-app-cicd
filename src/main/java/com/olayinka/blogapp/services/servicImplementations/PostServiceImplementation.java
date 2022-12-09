package com.olayinka.blogapp.services.servicImplementations;


import com.olayinka.blogapp.entities.Admin;
import com.olayinka.blogapp.entities.Post;
import com.olayinka.blogapp.entities.Rating;
import com.olayinka.blogapp.enums.Ratings;
import com.olayinka.blogapp.pojos.PostDto;
import com.olayinka.blogapp.repositories.AdminRepository;
import com.olayinka.blogapp.repositories.PostRepository;
import com.olayinka.blogapp.repositories.RatingsRepository;
import com.olayinka.blogapp.services.PostService;
import com.olayinka.blogapp.services.RatingsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service

@AllArgsConstructor
public class PostServiceImplementation implements PostService {

    private final PostRepository postRepository;
    private final AdminRepository adminRepository;
    private final RatingsRepository  ratingsRepository;

    private final RatingsService ratingsService;

    private final HttpSession httpSession;

    @Override
    public List<Post> allPost() {

        return postRepository.findAll();
    }

    @Override
    public String createAPost(PostDto postDto) {
        Long adminId =(Long) httpSession.getAttribute("adminID");
        Rating rating = new Rating();
        Post post = new Post();
        Admin admin;

        if (adminRepository.existsById(adminId)) {
            admin = adminRepository.findById(adminId).orElse(null);
            post.setAdmin(admin);
            System.out.println("Post created");

            System.out.println("admin wahala");
            rating.setPost(post);
            System.out.println("post wahala");

            BeanUtils.copyProperties(postDto, post);
          Post updatedPost=  postRepository.save(post);
            System.out.println("post has been saved");
         Rating updatedRating =   ratingsRepository.save(rating);

//            updatedPost.setRating(updatedRating);
            postRepository.save(updatedPost);





            return "Post successfully created";
        }
        return "Unable to create a post";
    }

    @Override
    public List<Post> allAdminPost() {
        Long id =(Long) httpSession.getAttribute("adminID");

        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        List<Post> allPost;


        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            allPost = postRepository.findAllByAdmin_Id(id);
            return allPost;
        }
        return null;
    }


    @Override
    public String updatePost(Long adminId, Long postId, PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent() & optionalPost.get().getAdmin().getId().equals(adminId)) {
            Post post = optionalPost.get();
            BeanUtils.copyProperties(postDto, post);
            postRepository.save(post);
            return "Post successfully updated";
        }

        return "Operation can not be performed";
    }

    @Override
    public String deleteAPost(Long adminId, Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (Objects.equals(post.getAdmin().getId(), postId)) {
                postRepository.delete(post);
                return "Post deleted successful";
            }
            return "Operation failed";
        }
        return "Operation failed";
    }

    @Override
    public String rateAPost(Ratings ratings, Long id) {
        System.out.println(ratings);
        Optional<Post> postOptional = postRepository.findById(id);
        System.out.println(postOptional.toString());
        Integer currentNoOfReviews;
        Ratings currentRating=null;
        Rating rating;

        HashMap<Ratings, Integer> match = new HashMap<>();

        match.put(Ratings.FIVE, 5);
        match.put(Ratings.FOUR, 4);
        match.put(Ratings.THREE, 3);
        match.put(Ratings.TWO, 2);
        match.put(Ratings.ONE, 1);
        Ratings newRatings;
        if(postOptional.isPresent()) {

            Post post= postOptional.get();

            rating = ratingsRepository.findByPost_Id(id);

//            Integer newRateValue = match.get(ratings);

            currentRating = post.getRatings();

               currentNoOfReviews = post.getNoOfReview();

            Integer newRateValue = match.get(ratings);

            ratingsService.fixRate(newRateValue, id);


            Long oneStar = newRateValue==1?  rating.getNumberOfOneStar() +1: rating.getNumberOfOneStar();
            Long twoStar = newRateValue==2?  rating.getNumberOfTwoStar()+1: rating.getNumberOfTwoStar();
            Long threeStar = newRateValue==3?  rating.getNumberOFThreeStar()+1 : rating.getNumberOFThreeStar();
            Long fourStar = newRateValue==4?  rating.getNumberOfFourStar()+1: rating.getNumberOfFourStar() ;
            Long fiveStart = newRateValue==5?  rating.getNumberOfFiveStar()+1 : rating.getNumberOfFiveStar();

            Long totalStar = oneStar+ twoStar+ threeStar+ fourStar+ fiveStart;


            if (currentRating.equals(Ratings.NO_RATING)){
                post.setRatings(ratings);
                post.setNoOfReview(post.getNoOfReview()+1);

                postRepository.save(post);
                ratingsService.fixRate(newRateValue, id);


            }

            else {

                Integer currentRateValue = match.get(currentRating);

                Integer finalResult = Math.toIntExact(((oneStar + twoStar * 2 + threeStar * 3 + fourStar * 4 + fiveStart * 5) / totalStar));




                for (Map.Entry<Ratings, Integer> rate : match.entrySet()) {
                    if (rate.getValue().equals(finalResult)) {
                        post.setRatings(rate.getKey());
                        System.out.println(rate.getKey().toString());
                        break;

                    }

                }

                post.setNoOfReview(post.getNoOfReview() + 1);
                postRepository.save(post);
            }
            return "Successful";


        }

        return "Unsuccessful";
    }
}
