package com.hoodiesbackend.entities.product.review.helpers;

import lombok.Data;

@Data
public class ReviewModifierHelper {

    private Long reviewId;

    private String message;

    private Integer score;
}
