package com.example.demo.repository;

import com.example.demo.dto.ReviewResponse;
import com.example.demo.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("""
        SELECT new com.example.demo.dto.ReviewResponse(
            r.id, r.stars, r.comment, r.image, p.id, p.nameShort, u.name
        )
        FROM Review r
        JOIN r.product p
        JOIN r.user u
    """)
    List<ReviewResponse> findAllReviews();

}
