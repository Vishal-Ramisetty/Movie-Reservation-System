package com.vrshowbiz.moviebuff.dto.request;

import com.vrshowbiz.moviebuff.model.Genre;
import com.vrshowbiz.moviebuff.model.Movie;
import com.vrshowbiz.moviebuff.model.Rating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

public class MovieRequest {

    @NotNull(message = "Movie Title cannot be null")
    private String title;

    @Valid
    @NotNull(message = "Movie Genre cannot be null")
    private Genre genre;

    @NotNull(message = "Released Date cannot be null")
    @PastOrPresent(message = "Released Date must be in the past or present")
    private Date date;

    @Valid
    @NotNull(message = "Movie Adult Guidance category be null")
    private Rating rating;

//    @NotNull(message = "Image cannot be Empty")
//    private MultipartFile moviePoster;

    public Movie toMovie(MultipartFile moviePoster) throws IOException {
        return  Movie.builder().movieName(title).genre(genre).rating(rating)
                .posterImage64(moviePoster.getBytes()).imageName(moviePoster.getOriginalFilename()).build();
    }

}
