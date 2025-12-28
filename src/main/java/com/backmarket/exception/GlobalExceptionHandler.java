package com.backmarket.exception;

import com.backmarket.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException (Exception ex){
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Error: " + ex.getMessage(), null
        ));
    }

}
