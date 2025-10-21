package com.vrshowbiz.moviebuff.repository;

import com.vrshowbiz.moviebuff.dto.response.MovieResponse;
import com.vrshowbiz.moviebuff.model.Genre;
import com.vrshowbiz.moviebuff.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

    // Use findBy + AttributeName + OrderBy + AttributeName + Asc/Desc
    // Example: List<Movie> findByGenreOrderByReleaseDateDesc(String genre);

    List<Movie> findAllByOrderByMovieNameAsc();

    Movie findByMovieName(String title);

    List<Movie> findByGenre(Genre genre);
}
