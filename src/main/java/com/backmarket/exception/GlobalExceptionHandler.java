package com.backmarket.exception;

import com.backmarket.dto.ApiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<?>> handleException (Exception ex){
        return ResponseEntity.ok(new ApiResponseDto<>(
                true, "Error: " + ex.getMessage(), null
        ));
    }

}
