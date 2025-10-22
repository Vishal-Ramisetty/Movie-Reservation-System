package com.vrshowbiz.moviebuff.controller;

import com.vrshowbiz.moviebuff.dto.request.MovieRequest;
import com.vrshowbiz.moviebuff.dto.response.MovieResponse;
import com.vrshowbiz.moviebuff.model.Movie;
import com.vrshowbiz.moviebuff.service.AdminService;
import jakarta.validation.Valid;
import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value="/movies", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MovieResponse> addMovie(@Valid @RequestPart("movieRequest") MovieRequest movieRequest,
                                                  @RequestPart("imageFile") MultipartFile imageFile){
        // Logic to add new movie and handle incorrect json input
        Movie movie= adminService.createMovie(movieRequest, imageFile);
        return ResponseEntity.ok().body((new MovieResponse()).toMovieResponse(movie));
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Expectation.None> deleteMovie(@PathVariable String movieId){
        adminService.deleteMovieById(movieId);
        return ResponseEntity.noContent().build();
    }

}
