package com.vrshowbiz.moviebuff.dto.request;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.vrshowbiz.moviebuff.model.Genre;
import com.vrshowbiz.moviebuff.model.Movie;
import com.vrshowbiz.moviebuff.model.Rating;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    @NotNull(message = "Movie Title cannot be null")
    private String title;

    @Valid
    @NotNull(message = "Movie Genre cannot be null")
    private String genre;

    @NotNull(message = "Released Date cannot be null")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @Valid
    @NotNull(message = "Movie Adult Guidance category be null")
    private Rating rating;

//    @NotNull(message = "Image cannot be Empty")
//    private MultipartFile moviePoster;

    public Movie toMovie(MultipartFile moviePoster) throws IOException {
        return  Movie.builder().movieName(title)
                .rating(rating).releasedDate(date)
                .genre(Genre.valueOf(genre.toUpperCase()))
//                .imageMetaData(generateUrlForImage(moviePoster))
                .build();
    }


    private String generateUrlForImage(MultipartFile imageFile) throws IOException {
        String originalFilename = imageFile.getOriginalFilename();

        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        }
        String filename = UUID.randomUUID() + extension;

        // 2. define path under static folder
        // assuming project root + resources + static
        Path uploadDir = Paths.get("src/main/resources/static/images/movies/Wallpaper");
        Files.createDirectories(uploadDir);  // ensure directories exist

        Path filePath = uploadDir.resolve(filename);
        imageFile.transferTo(filePath.toFile());

        // 3. compute URL (relative) to store
        String imageUrl = "/images/movies/Wallpaper" + filename;
        return imageUrl;
    }

}
