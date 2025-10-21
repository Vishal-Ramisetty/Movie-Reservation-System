package com.vrshowbiz.moviebuff.dto.request;

import com.vrshowbiz.moviebuff.exception.MovieNotFoundException;
import com.vrshowbiz.moviebuff.exception.UserNotFoundException;
import com.vrshowbiz.moviebuff.model.Movie;
import com.vrshowbiz.moviebuff.model.Review;
import com.vrshowbiz.moviebuff.model.User;
import com.vrshowbiz.moviebuff.service.MovieService;
import com.vrshowbiz.moviebuff.service.UserService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReviewRequest {

    private UserService userService;
    private MovieService movieService;

    @Autowired
    public ReviewRequest(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @NotNull(message = "Movie ID is mandatory to review a movie")
    private String movieId;

    @NotNull(message = "Anonymous reviews are not allowed. User ID is mandatory")
    private String userId;

    @NotNull(message = "Rating the movie out of 5 stars is mandatory")
    private String starRating;

    private String comment;

    public Review toReview() throws UserNotFoundException, MovieNotFoundException {
        Optional<User> user = userService.findUserById(userId);
        Optional<Movie> movie = movieService.findMovieById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        if(movie.isEmpty()) {
            throw new MovieNotFoundException("Movie with ID " + movieId + " not found");
        }
        return Review.builder().user(user.get()).movie(movie.get())
                .rating(Integer.parseInt(starRating)).comments(comment).build();
    }

}
