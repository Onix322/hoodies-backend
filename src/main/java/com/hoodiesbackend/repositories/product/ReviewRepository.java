package com.hoodiesbackend.repositories.product;

import com.hoodiesbackend.entities.product.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByProductIdAndScore(Long productId, Integer score);
    List<Review> findAllByProductId(Long productId);
    List<Review> findAllByUserId(Long userId);
}
