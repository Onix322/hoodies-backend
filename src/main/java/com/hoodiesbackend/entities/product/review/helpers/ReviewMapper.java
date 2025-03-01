package com.hoodiesbackend.entities.product.review.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.product.review.Review;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .message(review.getMessage())
                .product(ProductMapper.toDto(review.getProduct()))
                .user(UserMapper.toUserGetDto(review.getUser()))
                .score(review.getScore())
                .build();
    }
}
