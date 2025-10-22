package com.vrshowbiz.moviebuff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;


@Document(collection = "reviews")
@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor
@EnableMongoAuditing
public class Review {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

}
