package com.vrshowbiz.moviebuff.service;

import com.vrshowbiz.moviebuff.dto.request.ReviewRequest;
import com.vrshowbiz.moviebuff.exception.ReviewNotFoundException;
import com.vrshowbiz.moviebuff.model.Review;
import com.vrshowbiz.moviebuff.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {


    private ReviewRepository reviewRepository;
    private UserService userService;
    private MovieService movieService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository,UserService userService,MovieService movieService) {
        this.reviewRepository = reviewRepository;
        this.userService=userService;
        this.movieService=movieService;
    }

    public Review addReview(ReviewRequest reviewRequest) {
        Review review = reviewRequest.toReview(userService,movieService);
        reviewRepository.save(review);
        return review;
    }

    public Review findReviewById(String reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review Not Found with id: " + reviewId));
    }
    public Review updateReview(ReviewRequest reviewRequest) {
        Review updatedReview = reviewRequest.toReview(new UserService(), new MovieService());
        Review existingReview=findReviewById(String.valueOf(updatedReview.getId()));
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComments(updatedReview.getComments());
        reviewRepository.save(existingReview);
        return existingReview;
    }

    public void deleteReviewById(String reviewId) {
        Review review=findReviewById(reviewId);
        reviewRepository.delete(review);
    }

    public void deleteReviewsByMovieId(String movieId) {
        List<Review> reviews=reviewRepository.findByMovieId(UUID.fromString(movieId));
        reviews.stream().forEach(review -> reviewRepository.delete(review));
    }
}


