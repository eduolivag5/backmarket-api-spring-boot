package com.backmarket.repository;

import com.backmarket.dto.ReviewResponse;
import com.backmarket.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("""
        SELECT new com.backmarket.dto.ReviewResponse(
            r.id, r.stars, r.comment, r.image, p.id, p.nameShort, u.name
        )
        FROM Review r
        JOIN r.product p
        JOIN r.user u
    """)
    List<ReviewResponse> findAllReviews();

}
