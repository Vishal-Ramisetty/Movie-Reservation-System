package com.vrshowbiz.moviebuff.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionPayload {

    private String message;

    private boolean success;

    private Long timestamp;

    private HttpStatus status;

}
