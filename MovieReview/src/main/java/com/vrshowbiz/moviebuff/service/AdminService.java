package com.vrshowbiz.moviebuff.service;

import com.vrshowbiz.moviebuff.dto.request.MovieRequest;
import com.vrshowbiz.moviebuff.dto.response.MovieResponse;
import com.vrshowbiz.moviebuff.exception.ImageProcessingException;
import com.vrshowbiz.moviebuff.exception.MovieNotFoundException;
import com.vrshowbiz.moviebuff.exception.ReviewNotFoundException;
import com.vrshowbiz.moviebuff.model.Movie;
import com.vrshowbiz.moviebuff.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie createMovie(MovieRequest movieRequest, MultipartFile imageFile) {
        // Logic to create a new movie
        try {
            Movie movie = movieRequest.toMovie(imageFile);
            movieRepository.save(movie);
            return movie;
        } catch (IOException e) {
            throw new ImageProcessingException("Error processing the image file for the movie.");
        }
    }

    public void deleteMovieById(String id) {
        try{
            movieRepository.deleteById(UUID.fromString(id));
        }
        catch (MovieNotFoundException e)
        {
            throw new MovieNotFoundException("Movie Not Found with id: " + id);
        }
        catch (ReviewNotFoundException e){
            throw new ReviewNotFoundException("No Reviews found for the movie with id: " + id);
        }
    }
}
