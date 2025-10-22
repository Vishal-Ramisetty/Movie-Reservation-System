package com.vrshowbiz.moviebuff.controller;

import com.vrshowbiz.moviebuff.dto.response.MovieResponse;
import com.vrshowbiz.moviebuff.model.Genre;
import com.vrshowbiz.moviebuff.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    public MovieService movieService;

    @GetMapping("/home")
    public String home() {
        return "Welcome to MovieBuff Movie API";
    }

    @GetMapping("/search")
    public ResponseEntity<MovieResponse> getMovie(@RequestParam String title) {
        return ResponseEntity.ok()
                .body((new MovieResponse()).toMovieResponse(movieService.findMovieByTitle(title)));
    }

    @GetMapping("/")
    public ResponseEntity<List<MovieResponse>> getAllMovies() {

        // Refer https://www.baeldung.com/spring-data-jpa-pagination-sorting
        // Return a list of all movies - Max 5 - Use pagination and sorting by title
        // Handle case when no movies are found
        // Cache the top 5
        List<MovieResponse> movieResponseList =
                movieService.findAllMovies()
                        .stream()
                        .map(movie -> (new MovieResponse()).toMovieResponse(movie))
                        .limit(5)
                        .toList();
        return ResponseEntity.ok().body(movieResponseList);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<MovieResponse>> getMoviesByGenre(@RequestParam String genre
            , @RequestParam(required = false) boolean topRated, @RequestParam(required = false) String sortBy) {
        if (Arrays.stream(Genre.values()).noneMatch(g -> g.toString().equals(genre)))
            return ResponseEntity.noContent().build();

        List<MovieResponse> movieResponseList =
                movieService.findMoviesByGenre(genre)
                        .stream()
                        .map(movie -> (new MovieResponse()).toMovieResponse(movie))
                        .toList();

        // Sort By rating asc or desc
        if(sortBy!=null) {
            if (sortBy.equals("asc")) {
                movieResponseList = movieResponseList.stream()
                        .sorted(Comparator.comparing(MovieResponse::getTitle)).limit(5)
                        .collect(Collectors.toList());
            }
            if(sortBy.equals("desc")) {
                movieResponseList = movieResponseList.stream()
                        .sorted(Comparator.comparing(MovieResponse::getTitle).reversed()).limit(5)
                        .collect(Collectors.toList());
            }
        }

        // Get the top 5 rated movies in that genre
        if (topRated) {
            movieResponseList = movieResponseList.stream()
                    .sorted(Comparator.comparing(MovieResponse::getRated).reversed()).limit(5)
                    .collect(Collectors.toList());
        }

        return ResponseEntity.ok().body(movieResponseList);
    }
}
