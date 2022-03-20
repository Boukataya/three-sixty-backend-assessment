package com.threesixty.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@AllArgsConstructor
public class ApiError {

    private String message;
    private HttpStatus status;
    private Date timestamp;

}
