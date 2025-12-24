package com.example.demo.service;

import com.example.demo.dto.ReviewResponse;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewResponse> getReviews() {
        return  reviewRepository.findAllReviews();
    }

}
