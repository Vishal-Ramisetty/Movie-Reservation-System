package com.vrshowbiz.moviebuff.repository;

import com.vrshowbiz.moviebuff.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
//    public interface ReviewRepository extends MongoRepository<Review, String>{
    // can Define custom query methods here if needed

    List<Review> findByMovieId(UUID movieId);
}

