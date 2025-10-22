package com.vrshowbiz.moviebuff.exception;

import com.vrshowbiz.moviebuff.dto.response.ReviewResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex) {
        ExceptionPayload response = ExceptionPayload.builder().message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND).timestamp(System.currentTimeMillis())
                .success(false)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<?> handleMovieNotFoundException(MovieNotFoundException ex) {
        ExceptionPayload response = ExceptionPayload.builder().message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND).timestamp(System.currentTimeMillis())
                .success(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<?> handleReviewNotFoundException(ReviewNotFoundException ex) {
        ExceptionPayload response = ExceptionPayload.builder().message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND).timestamp(System.currentTimeMillis())
                .success(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ImageProcessingException.class)
    public ResponseEntity<?> handleImageProcessingException(ImageProcessingException ex) {
        ExceptionPayload response = ExceptionPayload.builder().message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND).timestamp(System.currentTimeMillis())
                .success(false)
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleGenericException(Exception ex) {
//        ExceptionPayload response = ExceptionPayload.builder().message(ex.getMessage())
//                .status(HttpStatus.BAD_REQUEST).timestamp(System.currentTimeMillis())
//                .success(false)
//                .build();
//        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }
}
