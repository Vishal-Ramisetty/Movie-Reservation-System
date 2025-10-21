package com.vrshowbiz.moviebuff.repository;

import com.vrshowbiz.moviebuff.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String>{

    // can Define custom query methods here if needed

}

