package com.vrshowbiz.moviebuff.dto.response;

import com.vrshowbiz.moviebuff.model.Movie;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieResponse {

    private String id;
    private String title;
    private String genre;
    private String likes;
    private String releasedDate;
    private String Rated;

    public MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder().id(movie.getId().toString()).genre(movie.getGenre().toString())
                        .likes(movie.getLikes().toString()).releasedDate(movie.getReleasedDate().toString())
                                .Rated(movie.getRating().toString()).title(movie.getMovieName())
                                        .build();
    }


}
