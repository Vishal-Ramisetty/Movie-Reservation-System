package com.vrshowbiz.moviebuff.controller;

import com.vrshowbiz.moviebuff.dto.request.ReviewRequest;
import com.vrshowbiz.moviebuff.dto.response.ReviewResponse;
import com.vrshowbiz.moviebuff.model.Review;
import com.vrshowbiz.moviebuff.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponse> addReview(@RequestBody ReviewRequest reviewRequest){
        // Logic to add new movie and handle incorrect json input
        Review review= reviewService.addReview(reviewRequest);
        return ResponseEntity.ok().body((new ReviewResponse()).toReviewResponse(review));
    }

//    @PatchMapping
//    public ResponseEntity<ReviewResponse> updateReview(@RequestBody ReviewRequest reviewRequest){
//
//    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable String reviewId){
        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.noContent().build();
    }
}
