package com.backmarket.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiResponse<T> {

    private boolean error;
    private String message;
    private T data;

    public ApiResponse() {}

    public ApiResponse(boolean error, String message, T data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

}
