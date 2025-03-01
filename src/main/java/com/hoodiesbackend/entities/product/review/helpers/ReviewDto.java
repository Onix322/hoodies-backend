package com.hoodiesbackend.entities.product.review.helpers;

import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReviewDto {

    private Long id;

    private String message;

    private Integer score;

    private ProductDto product;

    private UserGetDto user;
}
