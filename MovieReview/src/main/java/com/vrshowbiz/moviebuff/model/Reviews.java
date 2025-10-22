package com.vrshowbiz.moviebuff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;


// Ignore this Class that uses MongoDB as review storage

@Document(collection = "reviews")
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
@EnableMongoAuditing
public class Reviews {

    @MongoId
    private String id;

    @Min(1)
    @Max(5)
    private int rating;

    @Size(min = 1, max = 500)
    private String comments;

    //    @CreationTimestamp
    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

    // This relation might not work b/n SQL & MongoDB
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    //    @ManyToOne()
//    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

}
