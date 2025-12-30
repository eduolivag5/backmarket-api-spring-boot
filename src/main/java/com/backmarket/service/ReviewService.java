package com.backmarket.service;

import com.backmarket.dto.ReviewResponseDto;
import com.backmarket.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewResponseDto> getReviews() {
        return  reviewRepository.findAllReviews();
    }

}
