package com.vrshowbiz.moviebuff.dto.response;


import com.vrshowbiz.moviebuff.model.Review;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    String userName;
    String reviewId;
    String content;
    String lastModified;
    String starRating;

    public ReviewResponse toReviewResponse(Review review) {
        return ReviewResponse.builder()
                .userName(review.getUser().getFirstName()+" "+review.getUser().getLastName())
                .reviewId(review.getId())
                .content(review.getComments())
//                .lastModified(review.getUpdatedDate().toString())
                .starRating(String.valueOf(review.getRating()))
                .build();
    }

}
