package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.ReviewResponse;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviews() {
        return ResponseEntity.ok(new ApiResponse<>(
                false, "ok", reviewService.getReviews()
        ));
    }

}
