package com.vrshowbiz.moviebuff.dto.response;

import com.vrshowbiz.moviebuff.model.Movie;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private String id;
    private String title;
    private String genre;
//    private String likes;
    private String releasedDate;
    private String Rated;
    private List<ReviewResponse> reviews;

    public MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder().id(movie.getId().toString()).genre(movie.getGenre().toString())
                .releasedDate(movie.getReleasedDate().toString()) // .likes(movie.getLikes().toString())
                .Rated(movie.getRating().toString()).title(movie.getMovieName())
                .reviews(movie.getReviews().stream()
                        .map(review->(new ReviewResponse()).toReviewResponse(review))
                        .collect(java.util.stream.Collectors.toList()))
                .build();
    }


}
