package com.vrshowbiz.moviebuff.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name ="movie")
public class Movie {

    // Must Have Fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable =false, unique = true)
    private String movieName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    //Good to Have

    @Lob
    @Column(nullable =false)
    private byte[] posterImage64;

    @Column(name = "image_filename", length = 255)
    private String imageName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    // Nice to Have

    @Column(nullable = true)
    private Date releasedDate;

    @Column
    private volatile Long likes=0L;

    @Column
    private volatile Long disLikes=0L;

    // MongoDB and JPA relationship mapping commented out - Would require additional configuration
//    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
//    private List<Review> reviews;

}
