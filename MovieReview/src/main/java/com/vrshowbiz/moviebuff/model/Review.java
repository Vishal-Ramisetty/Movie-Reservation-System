package com.vrshowbiz.moviebuff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
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
public class Review {

    @MongoId
    private String id;

    @Min(1)
    @Max(5)
    private int rating;

    @Size(min=1, max=500)
    private String comments;

    @CreationTimestamp
    private Date createdDate;

    @UpdateTimestamp
    private Date updatedDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

}
