package com.vrshowbiz.moviebuff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name ="movies")
public class Movie {

    // Must Have Fields

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable =false, unique = true)
    private String movieName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    //Good to Have


    // Removed to have file system storage/ S3
//    @Lob
//    @Column(nullable =false)
//    private byte[] posterImageBytes;

    @Column(name = "image_filename", length = 255, nullable = true)
    private String imageMetaData;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rating rating;

    // Nice to Have

    @Column(nullable = false)
    @PastOrPresent(message = "Released Date must be in the past or present")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate releasedDate;

    @Column(nullable = true)
    private Long likes=0L;

    @Column(nullable = true)
    private Long disLikes=0L;

    // MongoDB and JPA relationship mapping commented out - Would require additional configuration
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    private List<Review> reviews;



}
