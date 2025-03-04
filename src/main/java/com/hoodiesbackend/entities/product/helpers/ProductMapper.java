package com.hoodiesbackend.entities.product.helpers;

import com.hoodiesbackend.entities.product.Product;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        return new ProductDto.Builder()
                .setId(product.getId())
                .setSize(product.getSize())
                .setRating(product.getRating())
                .setTitle(product.getTitle())
                .setPrice(product.getPrice())
                .setProductImage(product.getProductImages().get(0))
                .setAvailableForPurchase(product.getAvailableForPurchase())
                .setNumberReviews(product.getNumberReviews())
                .setProductColor(product.getProductColor())
                .build();

    }
}
