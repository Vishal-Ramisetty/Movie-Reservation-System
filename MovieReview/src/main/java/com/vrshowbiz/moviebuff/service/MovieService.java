package com.vrshowbiz.moviebuff.service;

import com.vrshowbiz.moviebuff.model.Genre;
import com.vrshowbiz.moviebuff.model.Movie;
import com.vrshowbiz.moviebuff.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> findMovieById(String movieId) {
        return movieRepository.findById(UUID.fromString(movieId));
    }

    public Movie findMovieByTitle(String title) {
        return movieRepository.findByMovieName(title);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAllByOrderByMovieNameAsc();
    }

    public List<Movie> findMoviesByGenre(String genre) {
        List<Movie> movieResponseList=
                movieRepository.findByGenre(Genre.valueOf(genre))
                        .stream()
                        .toList();
        return movieResponseList;
    }

}
